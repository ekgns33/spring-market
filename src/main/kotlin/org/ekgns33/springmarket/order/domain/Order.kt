package org.ekgns33.springmarket.order.domain

import java.time.LocalDateTime

class Order(
    val id: Long? = null,
    val sellerId: Long,
    val buyerId: Long,
    val orderLine: OrderLine,
    val requestedAt: LocalDateTime,
    var confirmedAt: LocalDateTime?,
    var status: OrderStatus = OrderStatus.REQUESTED,
) {
    companion object {
        fun withoutId(sellerId: Long, buyerId: Long, orderLine: OrderLine, status: OrderStatus): Order {
            return Order(null, sellerId, buyerId, orderLine, LocalDateTime.now(), LocalDateTime.now(), status)
        }
    }

    fun cancel(canceledBy: Long) {
        validateCancelable()
        validateCancellationPermission(canceledBy)
        updateStatus(OrderStatus.CANCELLED)
    }

    private fun validateCancelable() {
        check(this.status.isCancelable()) { "Order status $this is not cancelable" }
    }

    private fun updateStatus(status: OrderStatus) {
        this.status = status
    }

    private fun isBuyer(canceledBy: Long): Boolean {
        return buyerId == canceledBy
    }

    private fun isSeller(canceledBy: Long): Boolean {
        return sellerId == canceledBy
    }

    private fun validateCancellationPermission(userId: Long) {
        check(isBuyer(userId) || isSeller(userId)) { IllegalStateException("Buyer ID $buyerId is not valid") }
    }


    fun validateSeller(userId: Long) {
        check(this.sellerId == userId)
    }

    fun isOrderUpdatable(): Boolean {
        return this.status.isUpdatableStatus()
    }

    fun validateOrderConfirmation() {
        check(isOrderUpdatable()) { "주문 상태를 변경할 수 없습니다." }
    }

    fun confirm() {
        check(isOrderUpdatable())
        this.status = OrderStatus.CONFIRMED
        this.confirmedAt = LocalDateTime.now()
    }
}