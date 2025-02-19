package org.ekgns33.springmarket.user.adapter.`in`.model

data class SignupRequest (
    val name: String,
    val password: String
){
    init {
        require(name.isNotEmpty()) { "Name must not be empty" }
        require(!password.isEmpty()) { "Password must not be empty" }
    }
}
