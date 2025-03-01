package org.ekgns33.springmarket.product.persistence

import org.ekgns33.springmarket.product.service.dtos.ProductView
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<ProductEntity, Long> {

    @Query(
        "select " +
                "new org.ekgns33.springmarket.product.service.dtos.ProductView(p.id, p.name, p.price, p.quantity, p.reserved, p.sold, p.status) " +
                "from ProductEntity p"
    )
    fun findAllProductView(pageable: PageRequest): List<ProductView>
}
