package org.ekgns33.springmarket.product.adapter.`in`

import org.ekgns33.springmarket.common.BaseResponse.Companion.success
import org.ekgns33.springmarket.common.BaseResponse.SuccessResponse
import org.ekgns33.springmarket.product.adapter.`in`.model.ProductRegisterRequest
import org.ekgns33.springmarket.product.adapter.`in`.model.ProductRegisterResponse
import org.ekgns33.springmarket.product.service.port.`in`.ProductQueryUsecase
import org.ekgns33.springmarket.product.service.port.`in`.ProductRegisterUsecase
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
    fun registerProduct(@RequestBody registerRequest: ProductRegisterRequest)
            : ResponseEntity<SuccessResponse<ProductRegisterResponse>> {
        val response: ProductRegisterResponse = productRegisterUsecase.register(registerRequest.toRegisterCommand())
        return ResponseEntity.status(HttpStatus.CREATED).body(success(response, "success"))
    }

    @GetMapping
    fun listProducts(@RequestParam page: Int, @RequestParam size: Int)
            : ResponseEntity<SuccessResponse<ProductListViewResponse>> {
        val response: ProductListViewResponse = productQueryUsecase.fetchProducts(page, size)
        return ResponseEntity.ok(success(response, "success"))
    }

}