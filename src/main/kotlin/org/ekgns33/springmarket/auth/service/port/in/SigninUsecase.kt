package org.ekgns33.springmarket.auth.service.port.`in`

import org.ekgns33.springmarket.auth.adapter.`in`.model.SigninResponse

interface SigninUsecase {
    fun signin(id: String, password: String): SigninResponse
}