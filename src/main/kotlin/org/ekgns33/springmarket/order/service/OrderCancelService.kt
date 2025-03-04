package org.ekgns33.springmarket.order.service

import org.ekgns33.springmarket.order.adapter.`in`.OrderCancelResponse
import org.ekgns33.springmarket.order.domain.Order
import org.ekgns33.springmarket.order.service.port.`in`.OrderCancelCommand
import org.ekgns33.springmarket.order.service.port.`in`.OrderCancelUsecase
import org.ekgns33.springmarket.order.service.port.out.OrderLoadPort
import org.ekgns33.springmarket.order.service.port.out.OrderUserLoadPort
import org.ekgns33.springmarket.order.service.port.out.ProductCommandPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderCancelService(
    private val orderUserLoadPort: OrderUserLoadPort,
    private val orderLoadPort: OrderLoadPort,
    private val productCommandPort: ProductCommandPort

) : OrderCancelUsecase {

    @Transactional
    override fun cancel(orderCancelCommand: OrderCancelCommand): OrderCancelResponse {
        val order: Order = orderLoadPort.loadOrder(orderCancelCommand.orderId)
        val buyer = orderUserLoadPort.loadBuyer(order.buyerId)
        order.cancel(canceledBy = buyer.id)
        productCommandPort.cancelReservation(order.orderLine)
        return OrderCancelResponse(order.id!!)
    }
}