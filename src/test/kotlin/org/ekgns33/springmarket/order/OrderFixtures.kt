package org.ekgns33.springmarket.order

import org.ekgns33.springmarket.common.Money
import org.ekgns33.springmarket.order.domain.Order
import org.ekgns33.springmarket.order.domain.OrderLine
import org.ekgns33.springmarket.order.domain.OrderStatus
import java.time.LocalDateTime

class OrderFixtures {
    companion object {

        private fun getOrderLine(): OrderLine {
            return OrderLine(1L, Money(3000), 10, Money(30000))
        }

        fun getDefaultOrderWithBuyerAndSellerId(buyerId: Long, sellerId: Long): Order {
            return Order(
                id = null,
                sellerId = buyerId,
                buyerId = sellerId,
                orderLine = getOrderLine(),
                requestedAt = LocalDateTime.now(),
                confirmedAt = null,
                status = OrderStatus.REQUESTED
            )

        }
    }
}
