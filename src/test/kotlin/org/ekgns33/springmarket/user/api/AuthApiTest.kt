package org.ekgns33.springmarket.user.api

import com.fasterxml.jackson.databind.ObjectMapper
import org.ekgns33.springmarket.auth.adapter.`in`.model.SignInRequest
import org.ekgns33.springmarket.auth.adapter.`in`.model.SigninResponse
import org.ekgns33.springmarket.auth.service.port.`in`.SigninUsecase
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("인증 API 테스트")
class AuthApiTest {

    @MockitoBean
    lateinit var signinUsecase: SigninUsecase
    @Autowired
    lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    @DisplayName("로그인 요청 성공")
    fun 로그인_요청_성공() {
        val signinRequet = SignInRequest(
            email = "example@gmail.com",
            password = "test"
        )
        given(signinUsecase.signin(any(), any()))
            .willReturn(SigninResponse("token"))

        val result = mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signinRequet))
        )
        result
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
    }



}