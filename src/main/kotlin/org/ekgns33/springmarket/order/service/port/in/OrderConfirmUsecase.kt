package org.ekgns33.springmarket.order.service.port.`in`

interface OrderConfirmUsecase {
    fun confirmOrder(orderConfirmCommand: OrderConfirmCommand): OrderConfirmResponse
}
