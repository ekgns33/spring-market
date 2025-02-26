package org.ekgns33.springmarket.common

import org.ekgns33.springmarket.common.exceptions.ErrorCode

sealed class BaseResponse(
    open val status: Boolean,
    open val message: String,
) {

    companion object {
        fun <T> success(data: T, message: String): SuccessResponse<T> =
            SuccessResponse(data, status = true, message = message)

        fun error(message: String, errorCode: ErrorCode): ErrorResponse =
            ErrorResponse(errorCode = errorCode, status = false, message = message)
    }

    data class SuccessResponse<T>(
        val data: T?,
        override val status: Boolean,
        override val message: String = "success"
    ) : BaseResponse(status, message)

    data class ErrorResponse(
        val errorCode: ErrorCode,
        override val status: Boolean = false,
        override val message: String
    ) : BaseResponse(status, message)
}