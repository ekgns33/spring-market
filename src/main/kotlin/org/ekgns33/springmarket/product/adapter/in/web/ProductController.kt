package org.ekgns33.springmarket.product.adapter.`in`.web

import org.ekgns33.springmarket.common.BaseResponse.Companion.success
import org.ekgns33.springmarket.common.BaseResponse.SuccessResponse
import org.ekgns33.springmarket.common.Money
import org.ekgns33.springmarket.common.annotations.UserId
import org.ekgns33.springmarket.product.service.port.`in`.model.ProductDetailViewResponse
import org.ekgns33.springmarket.product.service.port.`in`.model.ProductListViewResponse
import org.ekgns33.springmarket.product.adapter.`in`.web.model.ProductRegisterRequest
import org.ekgns33.springmarket.product.service.port.`in`.model.ProductRegisterResponse
import org.ekgns33.springmarket.product.service.port.`in`.ProductQueryUsecase
import org.ekgns33.springmarket.product.service.port.`in`.ProductRegisterUsecase
import org.ekgns33.springmarket.product.service.port.`in`.model.ProductRegisterCommand
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/v1/products"])
class ProductController(
    private val productRegisterUsecase: ProductRegisterUsecase,
    private val productQueryUsecase: ProductQueryUsecase
) {

    @PostMapping
    fun registerProduct(
        @RequestBody registerRequest: ProductRegisterRequest,
        @UserId userId: Long
    )
            : ResponseEntity<SuccessResponse<ProductRegisterResponse>> {
        val response = productRegisterUsecase.register(createRegisterCommand(userId, registerRequest))
        return ResponseEntity.status(HttpStatus.CREATED).body(success(response, "success"))
    }

    @GetMapping
    fun listProducts(@RequestParam page: Int, @RequestParam size: Int)
            : ResponseEntity<SuccessResponse<ProductListViewResponse>> {
        val response = productQueryUsecase.fetchProducts(page, size)
        return ResponseEntity.ok(success(response, "success"))
    }

    @GetMapping("/{id}")
    fun getProductDetails(@PathVariable id: Long)
            : ResponseEntity<SuccessResponse<ProductDetailViewResponse>> {
        val response = productQueryUsecase.fetchProductDetails(id)
        return ResponseEntity.ok(success(response, "success"))
    }

    private fun createRegisterCommand(userId: Long, registerRequest: ProductRegisterRequest)
            : ProductRegisterCommand {
        return ProductRegisterCommand(
            sellerId = userId,
            name = registerRequest.name,
            quantity = registerRequest.quantity,
            price = Money(registerRequest.price)
        )
    }
}