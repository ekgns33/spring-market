package org.ekgns33.springmarket.product.domain

import org.ekgns33.springmarket.common.Money
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("Product 도메인 단위 테스트")
class ProductTest {
    private val dummySeller = Seller(1L, "판매자1")
    private val money = Money(1000)

    @Test
    @DisplayName("남은 재고가 올바르게 계산되어야 한다")
    fun `남은 재고 계산 테스트`() {
        val product = Product.withoutId(dummySeller, "테스트 상품", money, 10, ProductStatus.ON_SALE)
        product.reserved = 2
        product.sold = 3

        val leftStock = product.getLeftStock()

        assertEquals(5, leftStock)
    }

    @Test
    @DisplayName("판매 가능한 상태여야 한다")
    fun `판매 가능 여부 테스트`() {
        val product = Product.withoutId(dummySeller, "테스트 상품", money, 10, ProductStatus.ON_SALE)

        val saleable = product.isSaleable()

        assertTrue(saleable)
    }

    @Test
    @DisplayName("예약 - 재고 사용 시 재고가 정상적으로 감소되어야 한다")
    fun `재고 사용 테스트`() {
        val product = Product.withoutId(dummySeller, "테스트 상품", money, 10, ProductStatus.ON_SALE)

        product.makeReservation(3)

        assertEquals(7, product.getLeftStock())
        assertEquals(ProductStatus.ON_SALE, product.status)
    }

    @Test
    @DisplayName("예약 - 재고 사용 시 초과 주문이면 예외가 발생해야 한다")
    fun `재고 초과 주문 예외 테스트`() {
        val product = Product.withoutId(dummySeller, "테스트 상품", money, 5, ProductStatus.ON_SALE)

        val exception = assertThrows(IllegalStateException::class.java) {
            product.makeReservation(6)
        }
        assertEquals("초과 수량 주문입니다.", exception.message)
    }

    @Test
    @DisplayName("예약 -모든 재고가 예약되면 수량이 감소되고 상태가 업데이트되어야 한다")
    fun `전재고 예약 테스트`() {
        val product = Product.withoutId(dummySeller, "테스트 상품", money, 5, ProductStatus.ON_SALE)

        product.makeReservation(5)

        assertEquals(0, product.getLeftStock())
        assertEquals(ProductStatus.RESERVED, product.status)
    }

    @Test
    @DisplayName("예약 - 판매 후 남은 재고가 모두 예약되면 상태가 업데이트 된다.")
    fun `남은 재고 예약 테스트`() {
        val product = Product.withoutId(dummySeller, "테스트 상품", money, 5, ProductStatus.ON_SALE)
        product.makeReservation(1)
        product.confirmReservation(1)

        product.makeReservation(4)

        assertEquals(0, product.getLeftStock())
        assertEquals(ProductStatus.RESERVED, product.status)
    }

    @Test
    @DisplayName("예약 취소 - 예약 취소 시 예약 수량이 감소되고 상태가 업데이트되어야 한다")
    fun `예약 취소 테스트`() {
        val product = Product.withoutId(dummySeller, "테스트 상품", money, 10, ProductStatus.ON_SALE)
        product.reserved = 4
        product.sold = 3

        product.cancelReservation(2)

        assertEquals(2, product.reserved)
        assertEquals(5, product.getLeftStock())
        assertEquals(ProductStatus.ON_SALE, product.status)
    }

    @Test
    @DisplayName("예약 취소 - 예약 상품이 없는데 예약을 취소하면 예외를 발생시킨다")
    fun `예약 취소 예외 테스트`() {
        val product = Product.withoutId(dummySeller, "테스트 상품", money, 5, ProductStatus.ON_SALE)

        val exception = assertThrows(IllegalStateException::class.java) {
            product.cancelReservation(6)
        }
        assertEquals("예약된 물품의 개수를 초과합니다.", exception.message)
    }


    @Test
    @DisplayName("거래 확정 - 모든 재고가 거래 확정되면 잔여수량이 감소되고 상태가 업데이트되어야 한다")
    fun `전재고 거래 확정 테스트`() {
        val product = Product.withoutId(dummySeller, "테스트 상품", money, 5, ProductStatus.ON_SALE)

        product.makeReservation(5)
        product.confirmReservation(5)

        assertEquals(0, product.getLeftStock())
        assertEquals(ProductStatus.OUT_OF_STOCK, product.status)
    }
}
