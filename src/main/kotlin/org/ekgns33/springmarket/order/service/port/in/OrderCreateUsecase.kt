package org.ekgns33.springmarket.order.service.port.`in`

interface OrderCreateUsecase {
    fun createOrder(orderCreateCommand: OrderCreateCommand): OrderCreateResponse
}