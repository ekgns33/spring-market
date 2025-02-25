package org.ekgns33.springmarket.product.domain

import jakarta.persistence.*
import org.ekgns33.springmarket.common.BaseEntity

@Entity
@Table(name = "products")
class ProductEntity(
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: ProductStatus,

    @Column(name = "amount")
    var amount: Int,

    @Column(name = "price")
    var price: Int,

    @Column(name = "name")
    var name: String,

    ) : BaseEntity() {

    constructor(product: Product) : this(
        status = product.status,
        amount = product.amount,
        price = product.price.value,
        name = product.name
    ) {
        this.id = product.id
    }
}