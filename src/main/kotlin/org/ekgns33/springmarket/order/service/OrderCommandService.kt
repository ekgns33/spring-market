package org.ekgns33.springmarket.order.service

import lombok.extern.slf4j.Slf4j
import org.ekgns33.springmarket.common.logger
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
import org.ekgns33.springmarket.product.service.port.`in`.model.ProductStockUseCommand
import org.springframework.stereotype.Service

@Slf4j
@Service
class OrderCommandService(
    private val orderCreatePort: OrderCreatePort,
    private val orderUserLoadPort: OrderUserLoadPort,
    private val productLoadPort: ProductInfoLoadPort,
    private val productCommandPort: ProductCommandPort
) : OrderCreateUsecase {

    override fun createOrder(orderCreateCommand: OrderCreateCommand): OrderCreateResponse {
        val buyer = orderUserLoadPort.loadBuyer(orderCreateCommand.buyerId)
        val product = productLoadPort.loadProduct(orderCreateCommand.productId)
        logger().info(product.quantity.toString())
        logger().info(product.isSaleable().toString())
        check(product.isSaleable()) { "Product not saleable" }
        productCommandPort.useStock(ProductStockUseCommand(product.id, orderCreateCommand.quantity))
        val order = Order.withoutId(product.seller.id, buyer.id, OrderLine(product), OrderStatus.REQUESTED)
        val savedOrder = orderCreatePort.createOrder(order)
        return OrderCreateResponse(savedOrder.id, savedOrder.orderLine.productId)
    }

}