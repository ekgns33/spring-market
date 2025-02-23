package org.ekgns33.springmarket.auth.service

import org.ekgns33.springmarket.auth.adapter.`in`.model.SigninResponse
import org.ekgns33.springmarket.auth.jwts.JwtFactory
import org.ekgns33.springmarket.auth.service.port.`in`.SigninUsecase
import org.ekgns33.springmarket.auth.service.port.out.AuthInfoLoadPort
import org.springframework.stereotype.Service

@Service
class SigninService(
    private val authInfoLoadPort: AuthInfoLoadPort,
    private val jwtFactory: JwtFactory
) : SigninUsecase {
    override fun signin(email: String, password: String): SigninResponse {
        val authInfo = authInfoLoadPort.authenticate(email, password)
        return SigninResponse(jwtFactory.generateToken(authInfo))
    }
}