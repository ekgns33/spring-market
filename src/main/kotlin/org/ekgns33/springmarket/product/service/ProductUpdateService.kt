package org.ekgns33.springmarket.product.service

import org.ekgns33.springmarket.product.service.port.`in`.ProductUpdateUsecase
import org.ekgns33.springmarket.product.service.port.`in`.model.ProductStockUseCommand
import org.ekgns33.springmarket.product.service.port.out.ProductLoadPort
import org.ekgns33.springmarket.product.service.port.out.ProductSavePort
import org.springframework.stereotype.Service

@Service
class ProductUpdateService(
    private val productLoadPort: ProductLoadPort,
    private val productSavePort: ProductSavePort,
) : ProductUpdateUsecase{

    override fun useStockForReservation(productStockUseCommand: ProductStockUseCommand) {
        val product = productLoadPort.loadProduct(productStockUseCommand.productId)
        product.makeReservation(productStockUseCommand.quantity)
        productSavePort.save(product)
    }
}