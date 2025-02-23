package org.ekgns33.springmarket.auth.adapter.out

import org.ekgns33.springmarket.auth.domain.UserAuthInfo
import org.ekgns33.springmarket.auth.service.port.out.AuthInfoLoadPort
import org.ekgns33.springmarket.user.service.port.`in`.UserAuthUsecase
import org.springframework.stereotype.Component

@Component
class AuthInfoLoadAdapter(
    private val userPort: UserAuthUsecase
) : AuthInfoLoadPort {
    override fun authenticate(email: String, password: String): UserAuthInfo {
        val authInfo = userPort.authenticate(email, password)
        return UserAuthInfo(authInfo.id, authInfo.password, authInfo.userRole)
    }

    override fun loadByUsername(username: String): UserAuthInfo {
        return userPort.getUserAuthorities(username)
    }
}