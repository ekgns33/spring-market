package org.ekgns33.springmarket.product.domain

import jakarta.persistence.*
import org.ekgns33.springmarket.common.BaseEntity
import java.util.*

@Entity
@Table(name = "products")
class ProductEntity(
    @Column(name = "seller_id")
    var sellerId: Long,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: ProductStatus,

    @Column(name = "amount")
    var quantity: Int,

    @Column(name = "price")
    var price: Int,

    @Column(name = "name")
    var name: String,

    ) : BaseEntity() {

    constructor(product: Product) : this(
        sellerId = product.seller.id,
        status = product.status,
        quantity = product.quantity,
        price = product.price.value,
        name = product.name
    ) {
        this.id = product.id
    }

    override fun toString(): String {
        return "Product(id=$id, name='$name')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ProductEntity) return false
        if (other.javaClass != this.javaClass) return false
        if (id == null || other.id == null) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        if (id == null) return Objects.hash(name, price, status)
        return id.hashCode()
    }
}