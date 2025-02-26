package org.ekgns33.springmarket.user.service

import org.ekgns33.springmarket.user.service.port.`in`.UserQueryUsecase
import org.ekgns33.springmarket.user.service.port.`in`.model.UserInfo
import org.ekgns33.springmarket.user.service.port.out.UserLoadPort
import org.springframework.stereotype.Service

@Service
class UserQueryService(
    private val userLoadPort: UserLoadPort
) : UserQueryUsecase {
    override fun loadUser(userId: Long): UserInfo {
        val user = userLoadPort.findById(userId)
        return UserInfo(user.id!!, user.name)
    }
}