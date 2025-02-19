package org.ekgns33.springmarket.user.domain

class User(val name: String, val password: String) {
    init {
        require(name.isNotEmpty())
        require(password.isNotEmpty())
    }
}