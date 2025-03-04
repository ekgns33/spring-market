package org.ekgns33.springmarket.order.service.port.`in`

import org.ekgns33.springmarket.order.adapter.`in`.OrderCancelResponse

interface OrderCancelUsecase {
    fun cancel(orderCancelCommand: OrderCancelCommand): OrderCancelResponse
}
