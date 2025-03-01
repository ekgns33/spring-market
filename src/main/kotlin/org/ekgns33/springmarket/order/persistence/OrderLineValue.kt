package org.ekgns33.springmarket.order.persistence

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.ekgns33.springmarket.common.Money
import org.ekgns33.springmarket.order.domain.OrderLine

@Embeddable
data class OrderLineValue(
    @Column(name = "product_id")
    val productId: Long,
    @Column(name = "product_price")
    val price: Int,
    @Column(name = "ordered_quantity")
    val quantity: Int,
    @Column(name = "ordered_total_amount")
    val totalAmount: Int,
) {

    companion object {
        fun from(orderLine: OrderLine): OrderLineValue {
            return OrderLineValue(
                productId = orderLine.productId,
                price = orderLine.price.value,
                quantity = orderLine.quantity,
                totalAmount = orderLine.totalAmount.value
            )
        }
    }

    fun toOrderLine(): OrderLine {
        return OrderLine(
            productId = productId,
            price = Money(price),
            quantity = quantity,
            totalAmount = Money(totalAmount)
        )
    }
}
