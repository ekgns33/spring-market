package org.ekgns33.springmarket.order.adapter.`in`

import org.ekgns33.springmarket.common.BaseResponse.Companion.success
import org.ekgns33.springmarket.common.BaseResponse.SuccessResponse
import org.ekgns33.springmarket.common.annotations.UserId
import org.ekgns33.springmarket.order.adapter.`in`.model.OrderCreateRequest
import org.ekgns33.springmarket.order.service.port.`in`.OrderCreateCommand
import org.ekgns33.springmarket.order.service.port.`in`.OrderCreateResponse
import org.ekgns33.springmarket.order.service.port.`in`.OrderCreateUsecase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/v1/orders")
class OrderController(
    private val orderCreateUsecase: OrderCreateUsecase
) {

    @PostMapping
    fun createOrder(
        @RequestBody orderCreateRequest: OrderCreateRequest,
        @UserId userId: Long
    )
            : ResponseEntity<SuccessResponse<OrderCreateResponse>> {
        val response = orderCreateUsecase.createOrder(
            OrderCreateCommand(
                userId, orderCreateRequest.productId, orderCreateRequest.quantity
            )
        )
        return ResponseEntity.created(URI.create("/api/v1/orders/${response.orderId}"))
            .body(success(response, "success"))
    }
}