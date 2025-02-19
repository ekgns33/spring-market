package org.ekgns33.springmarket.user.service

import jakarta.transaction.Transactional
import org.ekgns33.springmarket.user.adapter.`in`.model.SignUpResponse
import org.ekgns33.springmarket.user.domain.User
import org.ekgns33.springmarket.user.service.port.`in`.UserService
import org.ekgns33.springmarket.user.service.port.out.UserCreatePort
import org.ekgns33.springmarket.user.service.port.`in`.model.UserSignupCommand
import org.springframework.stereotype.Service

@Service
class UserDomainService (private val userCreatePort: UserCreatePort) : UserService {

    @Transactional
    override fun signup(userSignupCommand: UserSignupCommand): SignUpResponse {
        val user = User.withoutId(userSignupCommand.name, userSignupCommand.password)
        val savedUserData = userCreatePort.save(user)
        return SignUpResponse(savedUserData.name, savedUserData.password)
    }

}