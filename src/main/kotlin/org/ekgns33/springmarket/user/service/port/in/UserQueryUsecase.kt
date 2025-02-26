package org.ekgns33.springmarket.user.service.port.`in`

import org.ekgns33.springmarket.user.service.port.`in`.model.UserInfo

interface UserQueryUsecase {
    fun loadUser(userId: Long): UserInfo
}