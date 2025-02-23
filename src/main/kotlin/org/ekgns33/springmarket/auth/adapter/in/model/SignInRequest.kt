package org.ekgns33.springmarket.auth.adapter.`in`.model

import org.ekgns33.springmarket.common.CustomValidation

data class SignInRequest(
    val email: String,
    val password: String
) {
    init {
        require(email.isNotBlank() && CustomValidation.isEmail(email))
        require(password.isNotBlank())
    }
}
