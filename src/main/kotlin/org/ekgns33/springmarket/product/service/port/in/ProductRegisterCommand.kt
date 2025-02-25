package org.ekgns33.springmarket.product.service.port.`in`

import org.ekgns33.springmarket.common.Money
import org.ekgns33.springmarket.product.domain.Product
import org.ekgns33.springmarket.product.domain.ProductStatus

data class ProductRegisterCommand(
    val name: String,
    val amount: Int,
    val price: Money
) {
    fun toProduct(): Product {
        return Product.withoutId(name, price, amount, ProductStatus.ON_SALE)
    }
}
