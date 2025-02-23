package org.ekgns33.springmarket.user.service.port.`in`

import org.ekgns33.springmarket.auth.domain.UserAuthInfo
import org.ekgns33.springmarket.user.adapter.`in`.model.SignUpResponse
import org.ekgns33.springmarket.user.service.port.`in`.model.UserSignupCommand

interface UserAuthUsecase {
    fun signup(userSignupCommand: UserSignupCommand): SignUpResponse
    fun authenticate(email: String, password: String): UserAuthInfo
    fun getUserAuthorities(username: String): UserAuthInfo
}