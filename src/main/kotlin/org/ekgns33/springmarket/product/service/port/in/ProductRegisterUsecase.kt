package org.ekgns33.springmarket.product.service.port.`in`

import org.ekgns33.springmarket.product.service.port.`in`.model.ProductRegisterResponse
import org.ekgns33.springmarket.product.service.port.`in`.model.ProductRegisterCommand

interface ProductRegisterUsecase {
    fun register(command: ProductRegisterCommand): ProductRegisterResponse
}