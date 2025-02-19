package org.ekgns33.springmarket.user.adapter.out

import org.ekgns33.springmarket.user.domain.User
import org.ekgns33.springmarket.user.domain.UserData
import org.ekgns33.springmarket.user.domain.UserEntity
import org.ekgns33.springmarket.user.persistence.UserRepository
import org.ekgns33.springmarket.user.service.port.out.UserCreatePort
import org.springframework.stereotype.Component

@Component
private class UserPersistenceAdapter (val userRepository: UserRepository): UserCreatePort {
    override fun save(userData: User): UserData {
        val userEntity = UserEntity(userData)
        userRepository.save(userEntity);
        return mapToUserData(userEntity);
    }

    private fun mapToUserData(userEntity: UserEntity): UserData {
        return UserData(userEntity.id, userEntity.name, userEntity.password)
    }
}