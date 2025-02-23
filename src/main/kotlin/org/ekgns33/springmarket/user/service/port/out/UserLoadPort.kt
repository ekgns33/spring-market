package org.ekgns33.springmarket.user.service.port.out

import org.ekgns33.springmarket.user.domain.User

interface UserLoadPort {
    fun findByName(name: String): User
    fun findByEmail(email: String): User
}