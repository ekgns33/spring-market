package org.ekgns33.springmarket.auth.adapter.`in`

import org.ekgns33.springmarket.auth.adapter.`in`.model.SignInRequest
import org.ekgns33.springmarket.auth.adapter.`in`.model.SigninResponse
import org.ekgns33.springmarket.common.BaseResponse
import org.ekgns33.springmarket.common.BaseResponse.SuccessResponse
import org.ekgns33.springmarket.auth.service.port.`in`.SigninUsecase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    val singinUsecase: SigninUsecase
) {
    @PostMapping("/signin")
    fun signin(@RequestBody signinRequest: SignInRequest): ResponseEntity<SuccessResponse<SigninResponse>> {
        val loginTokens = singinUsecase.signin(signinRequest.email, signinRequest.password)
        return ResponseEntity.ok(BaseResponse.success(loginTokens, "success"))
    }
}