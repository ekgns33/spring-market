package org.ekgns33.springmarket.product.domain

import org.ekgns33.springmarket.common.Money

class Product(
    val id: Long?,
    val seller: Seller,
    val name: String,
    val price: Money,
    val quantity: Int,
    val status: ProductStatus = ProductStatus.ON_SALE,
) {
    companion object {
        fun withoutId(seller: Seller, name: String, price: Money, quantity: Int, status: ProductStatus): Product {
            return Product(null, seller, name, price, quantity, status)
        }
    }

}