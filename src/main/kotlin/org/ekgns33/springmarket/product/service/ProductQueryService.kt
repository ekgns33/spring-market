package org.ekgns33.springmarket.product.service

import org.ekgns33.springmarket.product.service.port.`in`.model.ProductDetailViewResponse
import org.ekgns33.springmarket.product.service.port.`in`.model.ProductListViewResponse
import org.ekgns33.springmarket.product.service.port.`in`.ProductQueryUsecase
import org.ekgns33.springmarket.product.service.port.out.ProductLoadPort
import org.ekgns33.springmarket.product.service.port.out.ProductViewPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductQueryService(
    private val productLoadPort: ProductLoadPort,
    private val productViewPort: ProductViewPort
) : ProductQueryUsecase {

    @Transactional(readOnly = true)
    override fun fetchProducts(page: Int, size: Int): ProductListViewResponse {
        val productViews = productViewPort.loadProductViewList(page, size)
        return ProductListViewResponse(productViews)
    }

    @Transactional(readOnly = true)
    override fun fetchProductDetails(id: Long): ProductDetailViewResponse {
        val product = productLoadPort.loadProduct(id)
        //TODO: 해당 제품에 대한 거래내역이 있다면 거래내역 반환
        return ProductDetailViewResponse(product)
    }
}