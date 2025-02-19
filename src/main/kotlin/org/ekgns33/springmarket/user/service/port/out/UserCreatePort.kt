package org.ekgns33.springmarket.user.service.port.out

import org.ekgns33.springmarket.user.domain.User

interface UserCreatePort {
    fun save(userData: User) : User
}
