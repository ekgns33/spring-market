package org.ekgns33.springmarket.product.exceptions

import org.ekgns33.springmarket.common.exceptions.ErrorCode
import org.springframework.http.HttpStatus

enum class ProductErrorCode(
    override val description: String,
    override val responseCode: HttpStatus,
) : ErrorCode {
    PRODUCT_NOT_FOUND("제품이 존재하지 않습니다", HttpStatus.NOT_FOUND),
}