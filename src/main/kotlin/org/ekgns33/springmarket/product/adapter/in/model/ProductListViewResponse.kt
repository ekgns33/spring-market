package org.ekgns33.springmarket.product.adapter.`in`.model

import org.ekgns33.springmarket.product.service.dtos.ProductView


data class ProductListViewResponse(
    val products: List<ProductView>,
)
