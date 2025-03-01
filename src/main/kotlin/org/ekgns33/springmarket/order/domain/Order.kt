package org.ekgns33.springmarket.order.domain

import java.time.LocalDateTime

class Order(
    val id: Long? = null,
    val sellerId: Long,
    val buyerId: Long,
    val orderLine: OrderLine,
    val requestedAt: LocalDateTime,
    val confirmedAt: LocalDateTime?,
    val status: OrderStatus = OrderStatus.REQUESTED,
) {
    companion object {
        fun withoutId(sellerId: Long, buyerId: Long, orderLine: OrderLine, status: OrderStatus): Order {
            return Order(null, sellerId, buyerId, orderLine, LocalDateTime.now(), LocalDateTime.now(), status)
        }
    }
}