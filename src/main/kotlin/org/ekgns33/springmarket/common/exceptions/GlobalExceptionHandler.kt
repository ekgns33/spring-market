package org.ekgns33.springmarket.common.exceptions

import lombok.extern.slf4j.Slf4j
import org.ekgns33.springmarket.auth.exceptions.UserAuthenticationException
import org.ekgns33.springmarket.common.BaseResponse
import org.ekgns33.springmarket.common.BaseResponse.ErrorResponse
import org.ekgns33.springmarket.common.logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Slf4j
@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(UserAuthenticationException::class)
    fun handleUserAuthenticationException(e: UserAuthenticationException): ResponseEntity<ErrorResponse> {
        logger().info("[ERR] User Authentication Error : {} ", e.errorCode.name, e)
        return ResponseEntity.status(e.errorCode.responseCode).body(
            BaseResponse.error(e.errorCode.description, e.errorCode)
        )
    }

    @ExceptionHandler(BusinessServiceException::class)
    fun handleBusinessServiceException(e: BusinessServiceException): ResponseEntity<ErrorResponse> {
        logger().info("[ERR] Business Service Error : {} ", e.errorCode.description, e)
        return ResponseEntity.status(e.errorCode.responseCode).body(
            BaseResponse.error(e.errorCode.description, e.errorCode)
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        logger().error("[ERR] INTERNAL ERROR: ${e.message}", e)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            BaseResponse.error(GlobalErrorCode.SERVER_ERROR.name, GlobalErrorCode.SERVER_ERROR)
        )
    }
}