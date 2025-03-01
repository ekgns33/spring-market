package org.ekgns33.springmarket.order.adapter.out

import org.ekgns33.springmarket.order.domain.Order
import org.ekgns33.springmarket.order.persistence.OrderEntity
import org.ekgns33.springmarket.order.persistence.OrderRepository
import org.ekgns33.springmarket.order.service.port.out.OrderCreatePort
import org.springframework.stereotype.Component

@Component
class OrderPersistenceAdapter(
    private val orderRepository: OrderRepository,
) : OrderCreatePort {
    override fun createOrder(order: Order): Order {
        val orderEntity = orderRepository.save(OrderEntity(order))
        return OrderEntity.toOrder(orderEntity)
    }
}