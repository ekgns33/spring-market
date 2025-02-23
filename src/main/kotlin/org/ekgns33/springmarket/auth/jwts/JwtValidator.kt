package org.ekgns33.springmarket.auth.jwts

import io.jsonwebtoken.Jwts
import org.ekgns33.springmarket.auth.exceptions.UserAuthenticationException
import org.ekgns33.springmarket.common.ErrorCode
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Component
class JwtValidator(
    @Value("\${jwt.secret}") private val jwtSecret: String,
) {
    private val signingKey: SecretKeySpec
        get() {
            val keyBytes = Base64.getDecoder().decode(jwtSecret.toByteArray())
            return SecretKeySpec(keyBytes, 0, keyBytes.size, "HmacSHA256")
        }

    fun validate(token: String) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parse(token)
        } catch (e: Exception) {
            throw UserAuthenticationException(ErrorCode.JWT_BROKEN)
        }
    }

}
