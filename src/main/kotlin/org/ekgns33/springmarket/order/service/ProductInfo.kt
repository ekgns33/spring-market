package org.ekgns33.springmarket.order.service

import org.ekgns33.springmarket.common.Money
import org.ekgns33.springmarket.order.service.dtos.Seller
import org.ekgns33.springmarket.product.domain.Product

data class ProductInfo(
    val id: Long,
    val seller: Seller,
    val name: String,
    val price: Money,
    val quantity: Int,
    private val saleable: Boolean
) {
    fun isSaleable() = saleable

    companion object {
        fun from(product: Product) = ProductInfo(
            id = product.id!!,
            seller = Seller(product.seller.id, product.seller.name),
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            saleable = product.isSaleable()
        )
    }
}
