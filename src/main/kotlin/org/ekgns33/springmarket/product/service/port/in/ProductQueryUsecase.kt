package org.ekgns33.springmarket.product.service.port.`in`

import org.ekgns33.springmarket.product.adapter.`in`.model.ProductDetailViewResponse
import org.ekgns33.springmarket.product.adapter.`in`.model.ProductListViewResponse

interface ProductQueryUsecase {
    fun fetchProducts(page: Int, size: Int): ProductListViewResponse
    fun fetchProductDetails(id: Long): ProductDetailViewResponse
}
