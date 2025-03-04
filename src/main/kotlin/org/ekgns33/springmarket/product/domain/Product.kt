package org.ekgns33.springmarket.product.domain

import org.ekgns33.springmarket.common.Money

class Product(
    val id: Long?,
    val seller: Seller,
    var name: String,
    var price: Money,
    var quantity: Int,
    var reserved: Int = 0,
    var sold: Int = 0,
    var status: ProductStatus = ProductStatus.ON_SALE,
) {
    companion object {
        fun withoutId(seller: Seller, name: String, price: Money, quantity: Int, status: ProductStatus): Product {
            return Product(null, seller, name, price, quantity, 0, 0, status)
        }
    }

    fun getLeftStock() : Int {
        return quantity - reserved - sold
    }

    fun isSaleable(): Boolean {
        return isProductLeft() && status == ProductStatus.ON_SALE
    }

    fun makeReservation(quantity: Int) {
        check(isSaleable())
        check(this.quantity - quantity >= 0) { "초과 수량 주문입니다." }
        this.reserved += quantity
        updateStatus()
    }

    fun cancelReservation(quantity: Int) {
        check(isReservedProductLeft(quantity)) { "예약된 물품의 개수를 초과합니다."}
        this.reserved -= quantity
        updateStatus()
    }

    fun confirmReservation(quantity: Int) {
        check(isReservedProductLeft(quantity)) { "확정할 예약 상품이 부족합니다." }
        this.reserved -= quantity
        this.sold += quantity
        updateStatus()
    }

    private fun isReservedProductLeft(quantity: Int): Boolean {
        return this.reserved >= quantity
    }

    private fun updateStatus() {
        if (isProductLeft()) onSale()
        if (allReserved()) reserve()
        if (isOutOfStock()) outOfStock()
    }

    private fun onSale() {
        this.status = ProductStatus.ON_SALE
    }

    private fun reserve() {
        this.status = ProductStatus.RESERVED
    }

    private fun outOfStock() {
        this.status = ProductStatus.OUT_OF_STOCK
    }

    private fun allReserved(): Boolean {
        return getLeftStock() == 0 &&  sold != quantity
    }

    private fun isProductLeft() : Boolean {
        return (quantity - reserved - sold) > 0
    }

    private fun isOutOfStock(): Boolean {
        return quantity == sold && reserved == 0
    }

}