package org.ekgns33.springmarket.user.api

import com.fasterxml.jackson.databind.ObjectMapper
import org.ekgns33.springmarket.user.adapter.`in`.model.SignUpResponse
import org.ekgns33.springmarket.user.adapter.`in`.model.SignupRequest
import org.ekgns33.springmarket.user.service.port.`in`.UserService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("회원 API 테스트")
class UserApiTest {
    @MockitoBean
    lateinit var userService: UserService
    @Autowired
    lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    @DisplayName("회원 가입 요청 성공")
    fun 회원가입요청_성공() {
        val signUpRequest = SignupRequest(
            name = "사용자1",
            password = "비밀번호1"
        )
        given(userService.signup(any()))
            .willReturn(SignUpResponse("사용자1", "비밀번호1"))

        val result = mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest))
        )
        result
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
    }

}