package org.ekgns33.springmarket.order

import com.fasterxml.jackson.databind.ObjectMapper
import org.ekgns33.springmarket.order.adapter.`in`.OrderCancelResponse
import org.ekgns33.springmarket.order.adapter.`in`.model.OrderCreateRequest
import org.ekgns33.springmarket.order.service.port.`in`.*
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
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

    @MockitoBean
    private lateinit var orderConfirmUsecase: OrderConfirmUsecase


    @MockitoBean
    private lateinit var orderCancelUsecase: OrderCancelUsecase

    @DisplayName("주문 생성 API 테스트 - 성공")
    @Test
    @WithMockUser(username = "1", roles = ["USER"])
    fun 주문_생성() {
        val createOrderRequest = OrderCreateRequest(productId = 1L, 3)

        val expectedResponse = OrderCreateResponse(orderId = 1L, productId = 10L)
        whenever(orderCreateUsecase.createOrder(any<OrderCreateCommand>())).thenReturn(expectedResponse)

        val result = mockMvc.perform(
            post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createOrderRequest))
        )

        result.andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(header().string("Location", "/api/v1/orders/1"))
            .andExpect(jsonPath("$.data.productId").value(10L))

    }

    @DisplayName("주문 확정 API 테스트 - 성공")
    @Test
    @WithMockUser(username = "1", roles = ["USER"])
    fun 주문_확정() {

        whenever(orderConfirmUsecase.confirmOrder(any<OrderConfirmCommand>()))
            .thenReturn(OrderConfirmResponse(orderId = 1L))

        val result = mockMvc.perform(
            put("/api/v1/orders/${1}/confirm")
                .contentType(MediaType.APPLICATION_JSON)
        )

        result.andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.data.orderId").value(1L))

    }

    @DisplayName("주문 취소 API 테스트 - 성공")
    @Test
    @WithMockUser(username = "1", roles = ["USER"])
    fun `주문 취소`() {
        val orderId = 1L;
        whenever(orderCancelUsecase.cancel(any()))
            .thenReturn(OrderCancelResponse(orderId))

        val resultActions = mockMvc.perform(
            put("/api/v1/orders/$orderId/cancel")
                .contentType(MediaType.APPLICATION_JSON)
        )

        resultActions
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.data.orderId").value(1L))
    }
}
