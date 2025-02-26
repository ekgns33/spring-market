package org.ekgns33.springmarket.product.service

import org.ekgns33.springmarket.product.adapter.`in`.model.ProductRegisterResponse
import org.ekgns33.springmarket.product.domain.Product
import org.ekgns33.springmarket.product.domain.ProductStatus
import org.ekgns33.springmarket.product.domain.Seller
import org.ekgns33.springmarket.product.service.port.`in`.ProductRegisterUsecase
import org.ekgns33.springmarket.product.service.port.`in`.model.ProductRegisterCommand
import org.ekgns33.springmarket.product.service.port.out.ProductSavePort
import org.ekgns33.springmarket.product.service.port.out.UserLoadPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductCommandService(
    private val userLoadPort: UserLoadPort,
    private val productSavePort: ProductSavePort
) : ProductRegisterUsecase {

    @Transactional
    override fun register(command: ProductRegisterCommand): ProductRegisterResponse {
        val seller = userLoadPort.loadSeller(command.sellerId)
        val savedProduct = productSavePort.save(mapToProduct(command, seller))
        return ProductRegisterResponse(savedProduct)
    }

    private fun mapToProduct(command: ProductRegisterCommand, seller: Seller): Product {
        return Product.withoutId(
            seller = seller,
            name = command.name,
            price = command.price,
            quantity = command.quantity,
            status = ProductStatus.ON_SALE
        )
    }
}