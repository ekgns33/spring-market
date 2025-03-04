package org.ekgns33.springmarket.common

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("Validation 함수 테스트")
class CustomValidationTest {

    @DisplayName("이메일 정규표현식 검증 테스트 - 이메일형식이 아니라면 false 반환")
    @ParameterizedTest
    @ValueSource(strings = ["example@gmailcom", "@gmail.com", "examplegmailcom", "emailemail.com"])
    fun 이메일_검증_실패(email: String) {
        assertFalse(CustomValidation.isEmail(email))
    }

    @DisplayName("이메일 정규표현식 검증 테스트 - 이메일 형식이면 true 반환")
    @ParameterizedTest
    @ValueSource(strings = ["example@gmail.com", "test@naver.com"])
    fun 이메일_검증_성공(email: String) {
        assertTrue(CustomValidation.isEmail(email))
    }
}