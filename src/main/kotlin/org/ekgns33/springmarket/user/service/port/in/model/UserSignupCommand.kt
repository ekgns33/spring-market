package org.ekgns33.springmarket.user.service.port.`in`.model

data class UserSignupCommand(
    val email: String,
    val name: String,
    val password: String
)