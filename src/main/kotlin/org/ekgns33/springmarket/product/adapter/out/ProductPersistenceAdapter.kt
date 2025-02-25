package org.ekgns33.springmarket.product.adapter.out

import org.ekgns33.springmarket.common.Money
import org.ekgns33.springmarket.product.domain.Product
import org.ekgns33.springmarket.product.domain.ProductEntity
import org.ekgns33.springmarket.product.exceptions.ProductErrorCode
import org.ekgns33.springmarket.product.exceptions.ProductNotFoundException
import org.ekgns33.springmarket.product.service.port.out.ProductLoadPort
import org.ekgns33.springmarket.product.service.port.out.ProductSavePort
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class ProductPersistenceAdapter(
    private val productRepository: ProductRepository
) : ProductSavePort, ProductLoadPort {

    override fun save(product: Product): Product {
        val productEntity = ProductEntity(product)
        return mapToProduct(productRepository.save(productEntity))
    }

    override fun loadProducts(page: Int, size: Int): List<Product> {
        val pageable = PageRequest.of(page, size)
        val productEntities = productRepository.findAll(pageable)
            .map { mapToProduct(it) }
            .toList()
        return productEntities
    }

    override fun loadProduct(id: Long): Product {
        val productEntity = productRepository.findById(id)
            .orElseThrow { ProductNotFoundException(ProductErrorCode.PRODUCT_NOT_FOUND) }
        return mapToProduct(productEntity)
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