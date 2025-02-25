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

    override fun toString(): String {
        return "UserEntity(id = '$id', email='$email', name='$name')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserEntity) return false
        if (other.javaClass != this.javaClass) return false
        if (id == null || other.id == null) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: email.hashCode()
    }
}
