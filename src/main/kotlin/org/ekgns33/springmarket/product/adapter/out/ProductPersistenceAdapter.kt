package org.ekgns33.springmarket.product.adapter.out

import org.ekgns33.springmarket.common.Money
import org.ekgns33.springmarket.product.domain.Product
import org.ekgns33.springmarket.product.persistence.ProductEntity
import org.ekgns33.springmarket.product.domain.Seller
import org.ekgns33.springmarket.product.exceptions.ProductErrorCode
import org.ekgns33.springmarket.product.exceptions.ProductNotFoundException
import org.ekgns33.springmarket.product.persistence.ProductRepository
import org.ekgns33.springmarket.product.service.dtos.ProductView
import org.ekgns33.springmarket.product.service.port.out.ProductLoadPort
import org.ekgns33.springmarket.product.service.port.out.ProductSavePort
import org.ekgns33.springmarket.product.service.port.out.ProductUserLoadPort
import org.ekgns33.springmarket.product.service.port.out.ProductViewPort
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ProductPersistenceAdapter(
    private val productRepository: ProductRepository,
    private val productUserLoadPort: ProductUserLoadPort,
) : ProductSavePort, ProductLoadPort, ProductViewPort {

    @Transactional(readOnly = true)
    override fun save(product: Product): Product {
        val productEntity = productRepository.save(ProductEntity(product))
        val seller = productUserLoadPort.loadSeller(product.seller.id)
        return mapToProduct(productEntity, seller)
    }

    @Throws(OptimisticLockingFailureException::class)
    @Transactional
    override fun update(product: Product) {
        val productEntity = productRepository.findById(product.id!!)
            .orElseThrow { ProductNotFoundException(ProductErrorCode.PRODUCT_NOT_FOUND) }
        productEntity.update(product)
        productRepository.save(productEntity)
    }

    @Transactional(readOnly = true)
    override fun loadProduct(id: Long): Product {
        val productEntity = productRepository.findById(id)
            .orElseThrow { ProductNotFoundException(ProductErrorCode.PRODUCT_NOT_FOUND) }
        val seller = productUserLoadPort.loadSeller(productEntity.sellerId)
        return mapToProduct(productEntity, seller)
    }

    override fun loadProductViewList(page: Int, size: Int): List<ProductView> {
        val pageRequest = PageRequest.of(page, size)
        return productRepository.findAllProductView(pageRequest)
    }

    private fun mapToProduct(entity: ProductEntity, seller: Seller): Product {
        return Product(
            id = entity.id,
            seller = seller,
            name = entity.name,
            price = Money(entity.price),
            quantity = entity.quantity,
            reserved =  entity.reserved,
            sold = entity.sold,
            status = entity.status,
        )
    }
}