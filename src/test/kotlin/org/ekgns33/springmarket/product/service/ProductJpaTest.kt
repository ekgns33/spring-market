package org.ekgns33.springmarket.product.service

import org.ekgns33.springmarket.common.Money
import org.ekgns33.springmarket.product.adapter.out.ProductRepository
import org.ekgns33.springmarket.product.domain.Product
import org.ekgns33.springmarket.product.domain.ProductEntity
import org.ekgns33.springmarket.product.domain.ProductStatus
import org.ekgns33.springmarket.product.domain.Seller
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.domain.PageRequest
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@DataJpaTest
class ProductJpaTest {

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var testEntityManager: TestEntityManager

    @DisplayName("Product Entity 저장 insert 쿼리 테스트")
    @Test
    fun `제품 저장 후 조회`() {
        val product = Product.withoutId(Seller(1, "판매자1"), "상품명1", Money(1000), 10, ProductStatus.ON_SALE)
        val productEntity = ProductEntity(product)

        val savedEntity = testEntityManager.persistAndFlush(productEntity)

        testEntityManager.clear()

        val foundProduct = testEntityManager.find(ProductEntity::class.java, savedEntity.id)

        assertNotNull(foundProduct)
        assertEquals("상품명1", foundProduct.name)
        assertEquals(1000, foundProduct.price)
        assertEquals(10, foundProduct.quantity)
        assertNotNull(foundProduct.createdAt)
        assertNotNull(foundProduct.updatedAt)
        assertNull(foundProduct.deletedAt)
        assertEquals(ProductStatus.ON_SALE, foundProduct.status)
    }

    @DisplayName("Product Entity 조회 페이지네이션 쿼리 테스트")
    @Test
    fun 제품_목록_조회() {
        val totalProducts = 9
        val productList = ArrayList<ProductEntity>()
        for (i in 1..totalProducts) {
            val product = Product.withoutId(
                name = "제품명 $i",
                seller = Seller(1L, "판매자1"),
                price = Money(1000 + i),
                quantity = i,
                status = ProductStatus.ON_SALE
            )
            productList.add(ProductEntity(product))
        }
        productRepository.saveAll(productList)

        val firstPage = productRepository.findAll(PageRequest.of(0, 10))

        assertEquals(totalProducts, firstPage.numberOfElements)
    }

}
