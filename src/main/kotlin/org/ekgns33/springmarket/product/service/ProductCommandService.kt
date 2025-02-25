package org.ekgns33.springmarket.product.service

import org.ekgns33.springmarket.product.adapter.`in`.model.ProductRegisterResponse
import org.ekgns33.springmarket.product.domain.Product
import org.ekgns33.springmarket.product.service.port.`in`.ProductRegisterCommand
import org.ekgns33.springmarket.product.service.port.`in`.ProductRegisterUsecase
import org.ekgns33.springmarket.product.service.port.out.ProductSavePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductCommandService(
    private val productSavePort: ProductSavePort
) : ProductRegisterUsecase {

    @Transactional
    override fun register(command: ProductRegisterCommand): ProductRegisterResponse {
        val savedProduct: Product = productSavePort.save(command.toProduct())
        return ProductRegisterResponse(savedProduct)
    }
}