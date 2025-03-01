package org.ekgns33.springmarket.order.adapter.out

import org.ekgns33.springmarket.order.service.ProductInfo
import org.ekgns33.springmarket.order.service.port.out.ProductInfoLoadPort
import org.ekgns33.springmarket.product.service.port.out.ProductLoadPort
import org.springframework.stereotype.Component

@Component
class ProductLoadAdapter(
    private val productLoadPort: ProductLoadPort
) : ProductInfoLoadPort {
    override fun loadProduct(productId: Long): ProductInfo {
        val product = productLoadPort.loadProduct(productId)
        return ProductInfo.from(product)
    }
}