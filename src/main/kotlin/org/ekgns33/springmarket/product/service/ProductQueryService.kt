package org.ekgns33.springmarket.product.service

import org.ekgns33.springmarket.product.adapter.`in`.model.ProductDetailViewResponse
import org.ekgns33.springmarket.product.adapter.`in`.model.ProductListViewResponse
import org.ekgns33.springmarket.product.adapter.`in`.model.ProductView
import org.ekgns33.springmarket.product.domain.Product
import org.ekgns33.springmarket.product.service.port.`in`.ProductQueryUsecase
import org.ekgns33.springmarket.product.service.port.out.ProductLoadPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductQueryService(
    private val productLoadPort: ProductLoadPort
) : ProductQueryUsecase {

    @Transactional(readOnly = true)
    override fun fetchProducts(page: Int, size: Int): ProductListViewResponse {
        val products = productLoadPort.loadProducts(page, size)
        return ProductListViewResponse(mapToProductView(products))
    }

    @Transactional(readOnly = true)
    override fun fetchProductDetails(id: Long): ProductDetailViewResponse {
        val product = productLoadPort.loadProduct(id)
        //TODO: 해당 제품에 대한 거래내역이 있다면 거래내역 반환
        return ProductDetailViewResponse(product)
    }

    private fun mapToProductView(products: List<Product>): List<ProductView> {
        return products.stream()
            .map { ProductView(it) }
            .toList()
    }
}