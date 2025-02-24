package org.ekgns33.springmarket.product.domain

import jakarta.persistence.*

@Entity
@Table(name = "products")
class ProductEntity(
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: ProductStatus,

    @Column(name= "amount")
    var amount: Int,

    @Column(name= "price")
    var price: Int,

    @Column(name = "name")
    var name: String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long?
) {


    constructor(product: Product) : this(product.status, product.amount, product.price.value, product.name, product.id)
}