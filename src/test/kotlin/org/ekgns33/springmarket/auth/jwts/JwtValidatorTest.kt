package org.ekgns33.springmarket.auth.jwts

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*
import javax.crypto.spec.SecretKeySpec

import org.ekgns33.springmarket.auth.exceptions.UserAuthenticationException
import org.ekgns33.springmarket.common.GlobalErrorCode

@DisplayName("JWT Validator 검증")
class JwtValidatorTest {

 val testSecret = "teaksdsa12312dufyiasecjjvzx123123iuca123123iseunamsdnst";
 private val jwtValidator = JwtValidator(testSecret)


 private fun createValidToken(): String {
  val keyBytes = Base64.getDecoder().decode(testSecret.toByteArray())
  val signingKey = SecretKeySpec(keyBytes, 0, keyBytes.size, "HmacSHA256")
  return Jwts.builder()
   .setSubject("testuser")
   .setIssuedAt(Date())
   .setExpiration(Date(System.currentTimeMillis() + 60 * 1000)) // 1분 후 만료
   .signWith(signingKey, SignatureAlgorithm.HS256)
   .compact()
 }

 @Test
 fun `JWT 토큰 생성 테스트`() {
  val validToken = createValidToken()
  assertDoesNotThrow {
   jwtValidator.validate(validToken)
  }
 }

 @Test
 fun `Broken 토큰 입력 시 예외 발생`() {
  val invalidToken = "invalid.token.value"
  val exception = assertThrows(UserAuthenticationException::class.java) {
   jwtValidator.validate(invalidToken)
  }
  assertEquals(GlobalErrorCode.JWT_BROKEN, exception.errorCode)
 }

}