package org.ekgns33.springmarket.common.annotations

import org.ekgns33.springmarket.auth.exceptions.UserAuthenticationException
import org.ekgns33.springmarket.common.exceptions.GlobalErrorCode
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class UserIdArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(UserId::class.java) &&
                parameter.parameterType == Long::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Long {
        val authentication = SecurityContextHolder.getContext().authentication
        if(authentication == null || authentication.principal == null)
            throw UserAuthenticationException(GlobalErrorCode.NOT_AUTHENTICATED)
        val userId = (authentication?.principal as? UserDetails)?.username?.toLong()
        return userId ?: throw UserAuthenticationException(GlobalErrorCode.JWT_BROKEN)
    }
}
