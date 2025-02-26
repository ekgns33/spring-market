package org.ekgns33.springmarket.auth.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.ekgns33.springmarket.auth.jwts.JwtFactory
import org.ekgns33.springmarket.auth.jwts.JwtValidator
import org.ekgns33.springmarket.auth.service.CustomUserDetailsService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtValidator: JwtValidator,
    private val jwtFactory: JwtFactory,
    private val customUserDetailsService: CustomUserDetailsService
) : OncePerRequestFilter() {

    companion object {
        const val AUTH_TOKEN_HEADER: String = "Authorization"
        const val AUTH_TOKEN_HEADER_PREFIX = "Bearer "
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (request.getHeader((AUTH_TOKEN_HEADER)).isNullOrEmpty() || SecurityContextHolder.getContext().authentication != null) {
            filterChain.doFilter(request, response)
            return
        }
        val token = getJwtToken(request)
        jwtValidator.validate(token)
        val authentication = resolveAuthentication(token)
        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }

    private fun resolveAuthentication(token: String): UsernamePasswordAuthenticationToken {
        val username = jwtFactory.extractUsername(token)
        val userDetails: UserDetails = customUserDetailsService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(userDetails, userDetails.authorities)
    }

    private fun getJwtToken(request: HttpServletRequest): String {
        val bearerToken = request.getHeader(AUTH_TOKEN_HEADER)
        if(bearerToken.isNullOrEmpty() && !bearerToken.startsWith(AUTH_TOKEN_HEADER_PREFIX)) return ""
        return bearerToken.substring(7)
    }
}
