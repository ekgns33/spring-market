package org.ekgns33.springmarket.product.service

import org.ekgns33.springmarket.common.Money
import org.ekgns33.springmarket.product.domain.Product
import org.ekgns33.springmarket.product.domain.ProductStatus
import org.ekgns33.springmarket.product.domain.Seller
import org.ekgns33.springmarket.product.service.dtos.ProductView
import org.ekgns33.springmarket.product.service.port.`in`.ProductQueryUsecase
import org.ekgns33.springmarket.product.service.port.out.ProductLoadPort
import org.ekgns33.springmarket.product.service.port.out.ProductViewPort
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class ProductQueryServiceTest {

    class StubProductLoadPort : ProductLoadPort {
        override fun loadProduct(id: Long): Product {
            return Product(
                id = 2L,
                seller = Seller(1L, "판매자1"),
                name = "상품 2",
                price = Money(5000),
                quantity = 0,
                status = ProductStatus.OUT_OF_STOCK
            )
        }
    }

    class StubProductViewPort : ProductViewPort {
        override fun loadProductViewList(page: Int, size: Int): List<ProductView> {
            return listOf(
                ProductView(
                    id = 1L,
                    name = "상품 1",
                    price = 100000,
                    quantity = 100,
                    reserved = 1,
                    sold = 0,
                    status = ProductStatus.ON_SALE
                ),
                ProductView(
                    id = 2L,
                    name = "상품 2",
                    price = 5000,
                    quantity = 0,
                    reserved = 1,
                    sold = 0,
                    status = ProductStatus.OUT_OF_STOCK
                )
            )
        }
    }

    private val loadPort: ProductLoadPort = StubProductLoadPort()
    private val viewPort: ProductViewPort = StubProductViewPort()

    private val subject: ProductQueryUsecase = ProductQueryService(loadPort, viewPort)

    @DisplayName("상품 조회 테스트")
    @Test
    fun 상품_조회_테스트() {
        val queryPage = 0
        val queryPageSize = 10

        val response = subject.fetchProducts(queryPage, queryPageSize)

        assertEquals(2, response.products.size)
        assertEquals(100000, response.products[0].price)
        assertEquals(100, response.products[0].quantity)
        assertEquals("OUT_OF_STOCK", response.products[1].status)
    }
}