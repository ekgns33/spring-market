package org.ekgns33.springmarket.product

import com.fasterxml.jackson.databind.ObjectMapper
import org.ekgns33.springmarket.product.adapter.`in`.model.ProductRegisterRequest
import org.ekgns33.springmarket.product.adapter.`in`.model.ProductRegisterResponse
import org.ekgns33.springmarket.product.service.port.`in`.ProductRegisterUsecase
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class ProductApiTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockitoBean
    lateinit var productRegisterUsecase: ProductRegisterUsecase

    @DisplayName("상품 등록 API 테스트")
    @Test
    fun 상품_등록_성공() {

        val productRegisterRequest = ProductRegisterRequest(
            name = "상품명1",
            amount = 30,
            price = 1000
        )

        val expectedResponse = ProductRegisterResponse(
            id = 1L,
            name = "상품명1",
            amount = 30,
            price = 1000
        )
        whenever(productRegisterUsecase.register(any())).thenReturn(expectedResponse)

        val resultActions = mockMvc.perform(
            post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productRegisterRequest))
        )

        resultActions
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.data.id").value(1))
            .andExpect(jsonPath("$.data.name").value("상품명1"))
            .andDo { print(it.response.contentAsString) }

        verify(productRegisterUsecase, times(1)).register(any())
    }
}