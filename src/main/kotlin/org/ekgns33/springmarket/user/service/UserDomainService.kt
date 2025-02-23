package org.ekgns33.springmarket.user.service

import org.ekgns33.springmarket.auth.domain.UserAuthInfo
import org.ekgns33.springmarket.auth.exceptions.InvalidUserInfoException
import org.ekgns33.springmarket.user.adapter.`in`.model.SignUpResponse
import org.ekgns33.springmarket.user.domain.User
import org.ekgns33.springmarket.user.service.port.`in`.UserAuthUsecase
import org.ekgns33.springmarket.user.service.port.`in`.model.UserSignupCommand
import org.ekgns33.springmarket.user.service.port.out.UserCreatePort
import org.ekgns33.springmarket.user.service.port.out.UserLoadPort
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDomainService(
    private val userCreatePort: UserCreatePort,
    private val userLoadPort: UserLoadPort,
    private val passwordEncoder: PasswordEncoder
) : UserAuthUsecase {

    @Transactional
    override fun signup(userSignupCommand: UserSignupCommand): SignUpResponse {
        val hashedPassword = passwordEncoder.encode(userSignupCommand.password)
        val user = User.withoutId(userSignupCommand.email, userSignupCommand.name, hashedPassword)
        val savedUserData = userCreatePort.save(user)
        return SignUpResponse(savedUserData.email, savedUserData.name, savedUserData.password)
    }

    @Transactional(readOnly = true)
    override fun authenticate(email: String, password: String): UserAuthInfo {
        val user = userLoadPort.findByEmail(email)
        checkPassword(password, user.password)
        return UserAuthInfo(user.userId, user.password, listOf(user.role))
    }

    @Transactional(readOnly = true)
    override fun getUserAuthorities(username: String): UserAuthInfo {
        val user = userLoadPort.findByName(username)
        return UserAuthInfo(user.userId, user.password, listOf(user.role))
    }

    @Throws(AuthenticationException::class)
    private fun checkPassword(rawPassword: String, encodedPassword: String) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw InvalidUserInfoException()
        }
    }
}