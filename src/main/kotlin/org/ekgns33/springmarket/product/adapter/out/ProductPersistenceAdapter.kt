package org.ekgns33.springmarket.product.adapter.out

import org.ekgns33.springmarket.common.Money
import org.ekgns33.springmarket.product.domain.Product
import org.ekgns33.springmarket.product.domain.ProductEntity
import org.ekgns33.springmarket.product.service.port.out.ProductSavePort
import org.springframework.stereotype.Component

@Component
class ProductPersistenceAdapter(
    private val productRepository: ProductRepository
): ProductSavePort {

    override fun save(product: Product): Product {
        val productEntity = ProductEntity(product)
        return mapToProduct(productRepository.save(productEntity))
    }

    private fun mapToProduct(entity: ProductEntity): Product {
        return Product(
            id = entity.id,
            name = entity.name,
            price = Money(entity.price),
            amount = entity.amount,
            status = entity.status,
        )
    }
}