package org.ekgns33.springmarket.auth.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    // user의 고유 ID를 username으로 사용합니다.
    private val id: Long?,
    private val password: String,
    private val authorities: Collection<GrantedAuthority> = emptyList(),
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return id.toString()
    }

}