package org.ekgns33.springmarket.user.domain

import jakarta.persistence.*
import org.ekgns33.springmarket.common.BaseEntity

@Entity
@Table(name = "users")
class UserEntity(
    @Column(name = "email")
    var email: String,
    var name: String,
    var password: String,
    @Enumerated(EnumType.STRING)
    var role: UserRole = UserRole.USER,
) : BaseEntity() {
    constructor(user: User) : this(
        user.email,
        user.name,
        user.password,
        user.role,
    ) {
        this.id = user.id
    }
}
