package org.ekgns33.springmarket.order.adapter.`in`

import org.ekgns33.springmarket.common.BaseResponse.Companion.success
import org.ekgns33.springmarket.common.BaseResponse.SuccessResponse
import org.ekgns33.springmarket.common.annotations.UserId
import org.ekgns33.springmarket.order.adapter.`in`.model.OrderCreateRequest
import org.ekgns33.springmarket.order.service.port.`in`.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/orders")
class OrderController(
    private val orderCreateUsecase: OrderCreateUsecase,
    private val orderConfirmUsecase: OrderConfirmUsecase,
    private val orderCancelUsecase: OrderCancelUsecase
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

    @PutMapping("/{orderId}/confirm")
    fun confirmOrder(
        @PathVariable orderId: Long,
        @UserId userId: Long
    )
            : ResponseEntity<SuccessResponse<OrderConfirmResponse>> {
        val response = orderConfirmUsecase.confirmOrder(
            OrderConfirmCommand(
                userId, orderId
            )
        )
        return ResponseEntity.ok(success(response, "success"))
    }

    @PutMapping("/{orderId}/cancel")
    fun cancelOrder(
        @PathVariable orderId: Long,
        @UserId userId: Long
    )
            : ResponseEntity<SuccessResponse<OrderCancelResponse>> {
        val response = orderCancelUsecase.cancel(
            OrderCancelCommand(
                orderId = orderId,
                userId = userId
            )
        )
        return ResponseEntity.ok().body(success(response, "success"))
    }
}