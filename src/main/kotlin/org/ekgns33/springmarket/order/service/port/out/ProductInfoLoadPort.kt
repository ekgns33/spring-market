package org.ekgns33.springmarket.order.service.port.out

import org.ekgns33.springmarket.order.service.ProductInfo

interface ProductInfoLoadPort {
    fun loadProduct(productId: Long): ProductInfo
}
