package org.ekgns33.springmarket.user.domain

class User(
    val userId: Long?,
    val name: String,
    val password: String) {
    init {
        require(name.isNotEmpty())
        require(password.isNotEmpty())
    }

    companion object {
        fun withoutId(name: String, password: String): User {
            return User(null, name, password)
        }
    }

}