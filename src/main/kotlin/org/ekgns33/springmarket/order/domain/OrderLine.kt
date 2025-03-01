package org.ekgns33.springmarket.order.domain

import org.ekgns33.springmarket.common.Money
import org.ekgns33.springmarket.order.persistence.OrderLineValue
import org.ekgns33.springmarket.order.service.ProductInfo

data class OrderLine(
    val productId: Long,
    val price: Money,
    val quantity: Int,
    val totalAmount: Money
) {
    constructor(productInfo: ProductInfo) : this(
        productId = productInfo.id,
        price = productInfo.price,
        quantity = productInfo.quantity,
        totalAmount = productInfo.price.multiply(productInfo.quantity)
    )

    fun toOrderLineValue(): OrderLineValue {
        return OrderLineValue(
            productId = productId,
            price = price.value,
            quantity = quantity,
            totalAmount = totalAmount.value
        )
    }
}
