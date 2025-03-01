package org.ekgns33.springmarket.order

import com.fasterxml.jackson.databind.ObjectMapper
import org.ekgns33.springmarket.order.adapter.`in`.OrderController
import org.ekgns33.springmarket.order.adapter.`in`.model.OrderCreateRequest
import org.ekgns33.springmarket.order.service.port.`in`.OrderCreateCommand
import org.ekgns33.springmarket.order.service.port.`in`.OrderCreateResponse
import org.ekgns33.springmarket.order.service.port.`in`.OrderCreateUsecase
import org.ekgns33.springmarket.product.adapter.`in`.web.model.ProductRegisterRequest
import org.ekgns33.springmarket.user.adapter.`in`.web.model.SignupRequest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class OrderApiTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockitoBean
    lateinit var orderCreateUsecase: OrderCreateUsecase

    @DisplayName("주문 생성 API 테스트 - 성공")
    @Test
    @WithMockUser(username = "1", roles = ["USER"])
    fun 주문_생성() {
        val createOrderRequest = OrderCreateRequest(productId = 1L, 3)

        val expectedResponse = OrderCreateResponse(orderId = 1L, productId = 10L)
        whenever(orderCreateUsecase.createOrder(any<OrderCreateCommand>())).thenReturn(expectedResponse)

        val result =mockMvc.perform(
            post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createOrderRequest)))

        result.andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(header().string("Location", "/api/v1/orders/1"))
            .andExpect(jsonPath("$.data.productId").value(10L))

    }
}
