package org.ekgns33.springmarket.order.adapter.out

import org.ekgns33.springmarket.order.domain.Order
import org.ekgns33.springmarket.order.persistence.OrderEntity
import org.ekgns33.springmarket.order.persistence.OrderRepository
import org.ekgns33.springmarket.order.service.port.OrderUpdatePort
import org.ekgns33.springmarket.order.service.port.out.OrderCreatePort
import org.ekgns33.springmarket.order.service.port.out.OrderLoadPort
import org.springframework.stereotype.Component

@Component
class OrderPersistenceAdapter(
    private val orderRepository: OrderRepository,
) : OrderCreatePort, OrderUpdatePort, OrderLoadPort {
    override fun createOrder(order: Order): Order {
        val orderEntity = orderRepository.save(OrderEntity(order))
        return OrderEntity.toOrder(orderEntity)
    }

    override fun loadOrder(orderId: Long): Order {
        val orderEntity = orderRepository.findById(orderId)
            .orElseThrow { RuntimeException("Order with id $orderId does not exist") }
        return OrderEntity.toOrder(orderEntity)
    }

    override fun update(order: Order): Order {
        val orderEntity = OrderEntity(order)
        orderRepository.save(orderEntity)
        return OrderEntity.toOrder(orderEntity)
    }
}