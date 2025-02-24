package org.ekgns33.springmarket.product.adapter.`in`.model

import org.ekgns33.springmarket.common.Money
import org.ekgns33.springmarket.product.service.port.`in`.ProductRegisterCommand

data class ProductRegisterRequest(
    val name: String,
    val price: Int,
    val amount: Int,
) {
    fun toRegisterCommand(): ProductRegisterCommand {
        return ProductRegisterCommand(this.name, this.amount, Money(price))
    }
}
