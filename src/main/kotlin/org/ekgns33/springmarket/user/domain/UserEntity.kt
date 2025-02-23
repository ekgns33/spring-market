package org.ekgns33.springmarket.user.domain

import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Column(name = "email")
    var email: String,
    var name: String,
    var password: String,
    @Enumerated(EnumType.STRING)
    var role: UserRole = UserRole.USER,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
) {
    constructor(userDomain: User) : this(
        userDomain.email,
        userDomain.name,
        userDomain.password,
        userDomain.role,
    )
}
