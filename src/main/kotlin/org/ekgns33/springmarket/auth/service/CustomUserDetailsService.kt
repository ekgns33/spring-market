package org.ekgns33.springmarket.auth.service

import org.ekgns33.springmarket.auth.domain.CustomUserDetails
import org.ekgns33.springmarket.auth.service.port.out.AuthInfoLoadPort
import org.ekgns33.springmarket.user.domain.UserRole
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class CustomUserDetailsService (
    private val authInfoLoadPort: AuthInfoLoadPort
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val userInfo = authInfoLoadPort.loadByUsername(username)
        val authorities = getRoles(userInfo.userRole)
        return CustomUserDetails(userInfo.id, userInfo.password, authorities)
    }

    private fun getRoles(roles: List<UserRole>): List<SimpleGrantedAuthority> {
        return roles.stream()
            .map { SimpleGrantedAuthority(it.name) }
            .collect(Collectors.toList())
    }
}