package org.ekgns33.springmarket.product.adapter.`in`.model

import org.ekgns33.springmarket.product.domain.Product

// TODO: 거래 내역 추가
data class ProductDetailViewResponse(
    val id: Long?,
    val sellerId: Long,
    val sellerName: String,
    val name: String,
    val price: Int,
    val quantity: Int,
    val status: String,
) {
    constructor(product: Product) : this(
        id = product.id,
        sellerId = product.seller.id,
        sellerName = product.seller.name,
        name = product.name,
        price = product.price.value,
        quantity = product.quantity,
        status = product.status.name
    )
}
