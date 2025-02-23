package org.ekgns33.springmarket.user.persistence

import org.ekgns33.springmarket.user.domain.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByName(name: String): MutableList<UserEntity>
    fun findByEmail(email: String): MutableList<UserEntity>
}