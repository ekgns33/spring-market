package org.ekgns33.springmarket.product.service.port.`in`

import org.ekgns33.springmarket.product.adapter.`in`.model.ProductRegisterResponse

interface ProductRegisterUsecase {
    fun register(command: ProductRegisterCommand): ProductRegisterResponse
}