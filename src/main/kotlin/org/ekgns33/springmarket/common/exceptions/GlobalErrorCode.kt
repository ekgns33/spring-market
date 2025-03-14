package org.ekgns33.springmarket.common.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

enum class GlobalErrorCode(
    override val description: String,
    override val responseCode: HttpStatusCode
) : ErrorCode {
    SERVER_ERROR("서버 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND("존재하지 않는 페이지입니다.", HttpStatus.NOT_FOUND),
    BAD_REQUEST("잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    JWT_BROKEN("잘못된 사용자 정보입니다.", HttpStatus.BAD_REQUEST),
    NOT_AUTHENTICATED("인증이 필요합니다.", HttpStatus.UNAUTHORIZED),
}
