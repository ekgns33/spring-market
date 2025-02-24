package org.ekgns33.springmarket.product.adapter.out

import org.ekgns33.springmarket.product.domain.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<ProductEntity, Long> {
}
