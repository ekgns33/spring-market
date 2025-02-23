package org.ekgns33.springmarket.user.adapter.out

import jakarta.persistence.EntityNotFoundException
import org.ekgns33.springmarket.user.domain.User
import org.ekgns33.springmarket.user.domain.UserEntity
import org.ekgns33.springmarket.user.persistence.UserRepository
import org.ekgns33.springmarket.user.service.port.out.UserCreatePort
import org.ekgns33.springmarket.user.service.port.out.UserLoadPort
import org.springframework.stereotype.Component

@Component
private class UserPersistenceAdapter(val userRepository: UserRepository) : UserCreatePort, UserLoadPort {
    override fun save(userData: User): User {
        val userEntity = UserEntity(userData)
        userRepository.save(userEntity)
        return mapToUser(userEntity)
    }

    override fun findByEmail(email: String): User {
        val userEntity = userRepository.findByEmail(email).getOrNull(0) ?: throw UserNotFoundException()
        return mapToUser(userEntity)
    }

    override fun findByName(name: String): User {
        val userEntity = userRepository.findByName(name).getOrNull(0) ?: throw UserNotFoundException()
        return mapToUser(userEntity)
    }

    private fun mapToUser(userEntity: UserEntity): User {
        return User(userEntity.id, userEntity.email, userEntity.name, userEntity.password)
    }
}