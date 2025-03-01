package org.ekgns33.springmarket.user.adapter.`in`.web.model

import org.ekgns33.springmarket.common.CustomValidation

data class SignupRequest(
    val email: String,
    val name: String,
    val password: String
) {
    init {
        require(CustomValidation.isEmail(email))
        require(name.isNotEmpty()) { "Name must not be empty" }
        require(password.isNotEmpty()) { "Password must not be empty" }
    }
}
