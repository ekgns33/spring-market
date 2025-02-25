package org.ekgns33.springmarket.product.service.port.out

import org.ekgns33.springmarket.product.domain.Product

interface ProductLoadPort {
    fun loadProducts(page: Int, size: Int): List<Product>
    fun loadProduct(id: Long): Product
}
