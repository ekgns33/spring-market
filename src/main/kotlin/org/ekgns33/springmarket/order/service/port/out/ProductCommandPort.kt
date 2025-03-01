package org.ekgns33.springmarket.order.service.port.out

import org.ekgns33.springmarket.product.service.port.`in`.model.ProductStockUseCommand

interface ProductCommandPort {
    fun useStock(productStockUseCommand: ProductStockUseCommand)
}
