package org.ekgns33.springmarket.product.adapter.`in`.model

import org.ekgns33.springmarket.product.domain.Product

// TODO: 거래 내역 추가
data class ProductDetailViewResponse(
    val id: Long?,
    val name: String,
    val price: Int,
    val amount: Int,
    val status: String,
) {
    constructor(product: Product) : this(
        id = product.id,
        name = product.name,
        price = product.price.value,
        amount = product.amount,
        status = product.status.name
    )
}
