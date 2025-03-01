package org.ekgns33.springmarket.order.service.port.out

import org.ekgns33.springmarket.order.domain.Order

interface OrderCreatePort {
    fun createOrder(order: Order): Order
}
