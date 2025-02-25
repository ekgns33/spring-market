package org.ekgns33.springmarket.product.domain

import org.ekgns33.springmarket.common.Money

class Product(
    val id: Long?,
    val name: String,
    val price: Money,
    val amount: Int,
    val status: ProductStatus = ProductStatus.ON_SALE,
) {
    companion object {
        fun withoutId(name: String, price: Money, amount: Int, status: ProductStatus): Product {
            return Product(null, name, price, amount, status)
        }
    }

}