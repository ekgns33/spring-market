package org.ekgns33.springmarket.user.exceptions

import org.ekgns33.springmarket.common.exceptions.ErrorCode
import org.springframework.http.HttpStatus

enum class UserErrorCode(
    override val description: String,
    override val responseCode: HttpStatus,
) : ErrorCode {
    USER_NOT_FOUND("회원이 존재하지 않습니다", HttpStatus.NOT_FOUND),
}
