package org.ekgns33.springmarket.user.domain

import org.ekgns33.springmarket.common.CustomValidation

class User(
    val userId: Long?,
    val email: String,
    val name: String,
    val password: String,
    val role: UserRole = UserRole.USER,
) {
    init {
        require(email.isNotBlank() && CustomValidation.isEmail(email)) { "Email cannot be blank" }
        require(name.isNotEmpty())
        require(password.isNotEmpty())
    }

    companion object {
        fun withoutId(email: String, name: String, password: String): User {
            return User(null, email, name, password)
        }
    }

}