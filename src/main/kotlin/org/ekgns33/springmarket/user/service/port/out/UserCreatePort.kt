package org.ekgns33.springmarket.user.service.port.out

import org.ekgns33.springmarket.user.domain.User
import org.ekgns33.springmarket.user.domain.UserData

interface UserCreatePort {
    fun save(userData: User) : UserData
}
