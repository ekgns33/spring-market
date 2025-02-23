package org.ekgns33.springmarket.auth.service.port.out

import org.ekgns33.springmarket.auth.domain.UserAuthInfo

interface AuthInfoLoadPort {
    fun authenticate(email: String, password: String): UserAuthInfo
    fun loadByUsername(username: String): UserAuthInfo
}