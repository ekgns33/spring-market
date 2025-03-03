package org.ekgns33.springmarket.order.service

import org.ekgns33.springmarket.order.service.port.OrderUpdatePort
import org.ekgns33.springmarket.order.service.port.`in`.OrderConfirmCommand
import org.ekgns33.springmarket.order.service.port.`in`.OrderConfirmResponse
import org.ekgns33.springmarket.order.service.port.`in`.OrderConfirmUsecase
import org.ekgns33.springmarket.order.service.port.out.OrderLoadPort
import org.ekgns33.springmarket.order.service.port.out.OrderUserLoadPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderConfirmService(
    private val orderUserLoadPort: OrderUserLoadPort,
    private val orderLoadPort: OrderLoadPort,
    private val orderUpdatePort: OrderUpdatePort,
) : OrderConfirmUsecase {

    @Transactional
    override fun confirmOrder(orderConfirmCommand: OrderConfirmCommand): OrderConfirmResponse {
        val seller = orderUserLoadPort.loadSeller(orderConfirmCommand.userId)
        val order = orderLoadPort.loadOrder(orderConfirmCommand.orderId)
        order.validateSeller(seller.id)
        order.validateOrderConfirmation()
        order.confirm()
        orderUpdatePort.update(order)
        return OrderConfirmResponse(orderConfirmCommand.orderId)
    }
}