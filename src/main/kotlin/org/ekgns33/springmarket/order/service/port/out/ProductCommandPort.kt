package org.ekgns33.springmarket.order.service.port.out

import org.ekgns33.springmarket.order.domain.OrderLine

interface ProductCommandPort {
    fun useStock(orderLine: OrderLine)
    fun cancelReservation(orderLine: OrderLine)
}
