package org.ekgns33.springmarket.product.service

import org.ekgns33.springmarket.product.adapter.`in`.ProductListViewResponse
import org.ekgns33.springmarket.product.adapter.`in`.ProductView
import org.ekgns33.springmarket.product.domain.Product
import org.ekgns33.springmarket.product.service.port.`in`.ProductQueryUsecase
import org.ekgns33.springmarket.product.service.port.out.ProductLoadPort
import org.springframework.stereotype.Service

@Service
class ProductQueryService(
    private val productLoadPort: ProductLoadPort
) : ProductQueryUsecase {

    override fun fetchProducts(page: Int, size: Int): ProductListViewResponse {
        val products = productLoadPort.loadProducts(page, size)
        return ProductListViewResponse(mapToProductView(products))
    }

    private fun mapToProductView(products: List<Product>): List<ProductView> {
        return products.stream()
            .map { ProductView(it) }
            .toList()
    }
}