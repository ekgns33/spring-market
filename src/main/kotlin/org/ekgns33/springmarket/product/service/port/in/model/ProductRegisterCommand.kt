package org.ekgns33.springmarket.product.service.port.`in`.model

import org.ekgns33.springmarket.common.Money

data class ProductRegisterCommand(
    val sellerId: Long,
    val name: String,
    val amount: Int,
    val price: Money
)
