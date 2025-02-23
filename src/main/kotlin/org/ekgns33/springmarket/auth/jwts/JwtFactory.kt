package org.ekgns33.springmarket.auth.jwts

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.ekgns33.springmarket.auth.domain.UserAuthInfo
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Component
class JwtFactory(
    @Value("\${jwt.secret}") private val jwtSecret: String,
    @Value("\${jwt.expire_time}") private val jwtExpirationInMs: Long,
) {
    private val signingKey: SecretKeySpec
        get() {
            val keyBytes = Base64.getDecoder().decode(jwtSecret.toByteArray())
            return SecretKeySpec(keyBytes, 0, keyBytes.size, "HmacSHA256")
        }

    fun generateToken(userInfo: UserAuthInfo): String {
        val now = Date()
        return Jwts.builder()
            .signWith(signingKey)
            .setSubject(userInfo.id.toString())
            .setIssuedAt(now)
            .setExpiration(Date(now.time + jwtExpirationInMs))
            .compact()
    }

    fun extractUsername(token: String): String {
        return getClaimsFromToken(token).subject
    }

    private fun getClaimsFromToken(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(signingKey)
            .build()
            .parseClaimsJws(token)
            .body
    }
}