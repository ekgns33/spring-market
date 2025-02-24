package org.ekgns33.springmarket.product.adapter.`in`.model

import org.ekgns33.springmarket.product.domain.Product

data class ProductRegisterResponse(
    val id: Long?,
    val name: String,
    val price: Int,
    val amount: Int,
) {
    constructor(product: Product) : this(product.id, product.name, product.price.value, product.amount)
}
