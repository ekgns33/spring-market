package org.ekgns33.springmarket.user.domain

import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    var name: String,
    var password: String,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
) {
    constructor(userDomain: User) : this(
        userDomain.name,
        userDomain.password
    )
}
