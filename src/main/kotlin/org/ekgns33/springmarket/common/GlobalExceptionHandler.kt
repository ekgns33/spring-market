package org.ekgns33.springmarket.common

import lombok.extern.slf4j.Slf4j
import org.ekgns33.springmarket.common.BaseResponse.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Slf4j
@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        logger().error("[ERR] INTERNAL ERROR: ${e.message}", e)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            BaseResponse.error(ErrorCode.SERVER_ERROR.name, ErrorCode.SERVER_ERROR))
    }
}