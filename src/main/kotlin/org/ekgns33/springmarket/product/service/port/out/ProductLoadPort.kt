package org.ekgns33.springmarket.product.service.port.out

import org.ekgns33.springmarket.product.domain.Product

interface ProductLoadPort {
    fun loadProduct(id: Long): Product
}
