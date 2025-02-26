package org.ekgns33.springmarket.product.adapter.`in`.model

import org.ekgns33.springmarket.product.domain.Product

data class ProductRegisterResponse(
    val id: Long?,
    val sellerId: Long,
    val sellerName: String,
    val name: String,
    val price: Int,
    val amount: Int,
) {
    constructor(product: Product) : this(
        product.id,
        product.seller.id,
        product.seller.name,
        product.name,
        product.price.value,
        product.amount
    )
}
