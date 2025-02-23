package org.ekgns33.springmarket.user.api

import org.ekgns33.springmarket.common.CustomValidation


data class LoginRequest (
    val email: String,
    val password: String
){
    init {
        require(CustomValidation.isEmail(email))
        require(password.isNotBlank())
    }
}
