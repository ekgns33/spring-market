package org.ekgns33.springmarket.product.service.port.out

import org.ekgns33.springmarket.product.domain.Product

interface ProductSavePort {
    fun save(product: Product): Product
    fun update(product: Product)
}
