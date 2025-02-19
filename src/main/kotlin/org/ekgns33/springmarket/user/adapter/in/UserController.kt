package org.ekgns33.springmarket.user.adapter.`in`

import org.ekgns33.springmarket.common.BaseResponse.SuccessResponse
import org.ekgns33.springmarket.common.BaseResponse.Companion.success
import org.ekgns33.springmarket.user.adapter.`in`.model.SignUpResponse
import org.ekgns33.springmarket.user.adapter.`in`.model.SignupRequest
import org.ekgns33.springmarket.user.service.port.`in`.UserService
import org.ekgns33.springmarket.user.service.port.`in`.model.UserSignupCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    val userDomainService: UserService
) {
    @PostMapping
    fun signUp(@RequestBody signUpRequest: SignupRequest): ResponseEntity<SuccessResponse<SignUpResponse>> {
        println(signUpRequest.name +  signUpRequest.password)
        val signUpResponse = userDomainService.signup(UserSignupCommand(signUpRequest.name, signUpRequest.password))
        return ResponseEntity.status(201).body(success(signUpResponse, "success"))
    }
}