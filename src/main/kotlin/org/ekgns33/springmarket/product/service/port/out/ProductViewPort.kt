package org.ekgns33.springmarket.product.service.port.out

import org.ekgns33.springmarket.product.service.dtos.ProductView

interface ProductViewPort {
    fun loadProductViewList(page: Int, size: Int): List<ProductView>
}