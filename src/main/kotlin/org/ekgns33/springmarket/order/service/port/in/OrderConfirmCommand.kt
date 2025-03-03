package org.ekgns33.springmarket.order.service.port.`in`

data class OrderConfirmCommand(
    val orderId: Long,
    val userId: Long
)
