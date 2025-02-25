package org.ekgns33.springmarket.product.service

import org.ekgns33.springmarket.common.Money
import org.ekgns33.springmarket.product.domain.Product
import org.ekgns33.springmarket.product.domain.ProductStatus
import org.ekgns33.springmarket.product.service.port.`in`.ProductQueryUsecase
import org.ekgns33.springmarket.product.service.port.out.ProductLoadPort
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class ProductQueryServiceTest {

    class StubProductLoadPort : ProductLoadPort {
        override fun loadProducts(page: Int, size: Int): List<Product> {
            return listOf(
                Product(
                    id = 1L,
                    name = "상품 1",
                    price = Money(100000),
                    amount = 100,
                    status = ProductStatus.ON_SALE
                ),
                Product(
                    id = 2L,
                    name = "상품 2",
                    price = Money(5000),
                    amount = 0,
                    status = ProductStatus.OUT_OF_STOCK
                )
            )
        }

    }

    private val port: ProductLoadPort = StubProductLoadPort()

    private val subject: ProductQueryUsecase = ProductQueryService(port)

    @DisplayName("상품 조회 테스트")
    @Test
    fun 상품_조회_테스트() {
        val queryPage = 0
        val queryPageSize = 10

        val response = subject.fetchProducts(queryPage, queryPageSize)

        assertEquals(2, response.products.size)
        assertEquals(100000, response.products[0].price)
        assertEquals(100, response.products[0].amount)
        assertEquals("OUT_OF_STOCK", response.products[1].status)
    }


}