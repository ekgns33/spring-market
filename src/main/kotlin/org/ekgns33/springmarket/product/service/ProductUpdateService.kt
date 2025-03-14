package org.ekgns33.springmarket.product.service

import org.ekgns33.springmarket.product.aop.Retry
import org.ekgns33.springmarket.product.service.port.ProductStockRecoverCommand
import org.ekgns33.springmarket.product.service.port.`in`.ProductUpdateUsecase
import org.ekgns33.springmarket.product.service.port.`in`.model.ProductStockUseCommand
import org.ekgns33.springmarket.product.service.port.out.ProductLoadPort
import org.ekgns33.springmarket.product.service.port.out.ProductSavePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class ProductUpdateService(
    private val productLoadPort: ProductLoadPort,
    private val productSavePort: ProductSavePort,
) : ProductUpdateUsecase{

    @Retry
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun useStockForReservation(productStockUseCommand: ProductStockUseCommand) {
        val product = productLoadPort.loadProduct(productStockUseCommand.productId)
        product.makeReservation(productStockUseCommand.quantity)
        productSavePort.update(product)
    }

    @Retry
    @Transactional
    override fun recoverStockForReservation(productStockRecoverCommand: ProductStockRecoverCommand) {
        val product = productLoadPort.loadProduct(productStockRecoverCommand.productId)
        product.cancelReservation(productStockRecoverCommand.recoverAmount)
        productSavePort.update(product)
    }
}