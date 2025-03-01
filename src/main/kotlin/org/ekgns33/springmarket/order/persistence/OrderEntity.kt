package org.ekgns33.springmarket.order.persistence

import jakarta.persistence.*
import org.ekgns33.springmarket.common.BaseEntity
import org.ekgns33.springmarket.order.domain.Order
import org.ekgns33.springmarket.order.domain.OrderStatus
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class OrderEntity(
    @Column(name = "buyer_id")
    var buyerId: Long,

    @Column(name = "seller_id")
    var sellerId: Long,

    @Embedded
    var orderLine: OrderLineValue,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: OrderStatus,

    @Column(name = "confirmed_at")
    var confirmedAt: LocalDateTime? = null,

    @Column(name = "requested_at")
    var requestedAt: LocalDateTime,
) : BaseEntity() {

    companion object {
        fun toOrder(order: OrderEntity): Order {
            return Order(
                id = order.id,
                buyerId = order.buyerId,
                sellerId = order.sellerId,
                orderLine = order.orderLine.toOrderLine(),
                status = order.status,
                confirmedAt = order.confirmedAt,
                requestedAt = order.requestedAt,
            )
        }
    }

    constructor(order: Order) : this(
        buyerId = order.buyerId,
        sellerId = order.sellerId,
        orderLine = order.orderLine.toOrderLineValue(),
        status = order.status,
        confirmedAt = order.confirmedAt,
        requestedAt = order.requestedAt,
    )

}
