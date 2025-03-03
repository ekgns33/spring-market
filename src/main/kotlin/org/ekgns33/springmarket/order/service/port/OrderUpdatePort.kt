package org.ekgns33.springmarket.order.service.port

import org.ekgns33.springmarket.order.domain.Order

interface OrderUpdatePort {
    fun update(order: Order): Order
}
