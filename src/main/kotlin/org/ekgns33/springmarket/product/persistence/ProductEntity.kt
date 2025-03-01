package org.ekgns33.springmarket.product.persistence

import jakarta.persistence.*
import org.ekgns33.springmarket.common.BaseEntity
import org.ekgns33.springmarket.product.domain.Product
import org.ekgns33.springmarket.product.domain.ProductStatus
import java.util.*

@Entity
@Table(name = "products")
class ProductEntity(
    @Column(name = "seller_id")
    var sellerId: Long,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: ProductStatus,

    @Column(name = "total_quantity")
    var quantity: Int,

    @Column(name = "reserved_quantity")
    var reserved: Int,

    @Column(name = "sold_quantity")
    var sold: Int,

    @Column(name = "price")
    var price: Int,

    @Column(name = "name")
    var name: String,

    ) : BaseEntity() {

    constructor(product: Product) : this(
        sellerId = product.seller.id,
        status = product.status,
        quantity = product.quantity,
        reserved = product.reserved,
        sold = product.sold,
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