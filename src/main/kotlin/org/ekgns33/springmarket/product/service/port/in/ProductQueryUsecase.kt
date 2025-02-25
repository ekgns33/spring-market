package org.ekgns33.springmarket.product.service.port.`in`

import org.ekgns33.springmarket.product.adapter.`in`.ProductListViewResponse

interface ProductQueryUsecase {
    fun fetchProducts(page: Int, size: Int): ProductListViewResponse
}
