package org.ekgns33.springmarket.product.service.dtos

import org.ekgns33.springmarket.product.domain.ProductStatus


data class ProductView(
    val id: Long?,
    val name: String,
    val price: Int,
    val amount: Int,
    val status: String,
) {
    constructor(id: Long?, name: String, price: Int, amount: Int, status: ProductStatus) :
            this(id, name, price, amount, status.name)
}
