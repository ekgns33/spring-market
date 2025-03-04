package org.ekgns33.springmarket.product.service.port.`in`

import org.ekgns33.springmarket.product.service.port.ProductStockRecoverCommand
import org.ekgns33.springmarket.product.service.port.`in`.model.ProductStockUseCommand

interface ProductUpdateUsecase {
    fun useStockForReservation(productStockUseCommand: ProductStockUseCommand)
    fun recoverStockForReservation(productStockRecoverCommand: ProductStockRecoverCommand)
}