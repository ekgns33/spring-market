package org.ekgns33.springmarket.order.service.port.`in`

data class OrderCreateCommand(
    val buyerId: Long,
    val productId: Long,
    val quantity: Int,
)
