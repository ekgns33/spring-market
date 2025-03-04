package org.ekgns33.springmarket.order.service

import org.ekgns33.springmarket.order.domain.Order
import org.ekgns33.springmarket.order.domain.OrderLine
import org.ekgns33.springmarket.order.domain.OrderStatus
import org.ekgns33.springmarket.order.service.port.`in`.OrderCreateCommand
import org.ekgns33.springmarket.order.service.port.`in`.OrderCreateResponse
import org.ekgns33.springmarket.order.service.port.`in`.OrderCreateUsecase
import org.ekgns33.springmarket.order.service.port.out.OrderCreatePort
import org.ekgns33.springmarket.order.service.port.out.OrderUserLoadPort
import org.ekgns33.springmarket.order.service.port.out.ProductCommandPort
import org.ekgns33.springmarket.order.service.port.out.ProductInfoLoadPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderCreateService(
    private val orderCreatePort: OrderCreatePort,
    private val orderUserLoadPort: OrderUserLoadPort,
    private val productLoadPort: ProductInfoLoadPort,
    private val productCommandPort: ProductCommandPort
) : OrderCreateUsecase {

    @Transactional
    override fun createOrder(orderCreateCommand: OrderCreateCommand): OrderCreateResponse {
        val buyer = orderUserLoadPort.loadBuyer(orderCreateCommand.buyerId)
        val product = productLoadPort.loadProduct(orderCreateCommand.productId)
        check(product.isSaleable()) { "Product not saleable" }
        val order = Order.withoutId(
            sellerId = product.seller.id,
            buyerId = buyer.id,
            orderLine = OrderLine(product, orderCreateCommand.quantity),
            status = OrderStatus.REQUESTED
        )
        productCommandPort.useStock(order.orderLine)
        val savedOrder = orderCreatePort.createOrder(order)
        return OrderCreateResponse(savedOrder.id, savedOrder.orderLine.productId)
    }
}