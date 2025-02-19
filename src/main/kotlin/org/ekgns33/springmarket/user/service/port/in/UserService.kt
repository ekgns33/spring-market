package org.ekgns33.springmarket.user.service.port.`in`

import org.ekgns33.springmarket.user.adapter.`in`.model.SignUpResponse
import org.ekgns33.springmarket.user.service.port.`in`.model.UserSignupCommand

interface UserService {
    fun signup(userSignupCommand: UserSignupCommand): SignUpResponse
}