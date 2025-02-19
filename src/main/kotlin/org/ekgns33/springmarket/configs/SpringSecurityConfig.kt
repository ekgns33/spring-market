package org.ekgns33.springmarket.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.util.PathMatcher


@Configuration
@EnableWebSecurity
class SpringSecurityConfig (
){

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity, pathMatcher: PathMatcher): SecurityFilterChain {
        http {
            csrf {
                disable()
            }
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
            securityMatcher("/api/v1/auth/**")
            authorizeHttpRequests {
                authorize(anyRequest, permitAll)
            }
            securityMatcher("/api/v1/**")
            authorizeHttpRequests {
                authorize("/users", permitAll)
                authorize(anyRequest, authenticated)
            }

        }
        return http.build()
    }


}