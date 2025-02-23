package org.ekgns33.springmarket.auth.domain

import org.ekgns33.springmarket.user.domain.UserRole

data class UserAuthInfo(
    val id: Long?,
    val password: String,
    val userRole: List<UserRole>,
)
