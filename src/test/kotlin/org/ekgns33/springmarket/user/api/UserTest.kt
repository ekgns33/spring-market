package org.ekgns33.springmarket.user.api

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertFails
import org.ekgns33.springmarket.user.domain.User;
import org.ekgns33.springmarket.user.service.UserDomainService

@DisplayName("사용자 도메인 테스트")
class UserTest(
    private val userDomainService: UserDomainService){

    @ParameterizedTest()
    @CsvSource(
        "사용자1, ''",
        "'', 비밀번호2"
    )
    fun 회원가입_입력값_검증(name: String, password: String) {
        assertFails {
            val user = User(name, password)
        }
    }
}

