package org.ekgns33.springmarket.user.api

import org.ekgns33.springmarket.user.domain.User
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertFails

@DisplayName("사용자 도메인 테스트")
class UserTest {

    @ParameterizedTest()
    @CsvSource(
        "email, 사용자1, ''",
        "test@example.com, '', 비밀번호2"
    )
    fun 회원가입_입력값_검증(email: String, name: String, password: String) {
        assertFails {
            val user = User.withoutId(email, name, password)
        }
    }
}

