package org.ekgns33.springmarket.common

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

enum class ErrorCode (
    val errorCode: String,
    val responseCode: HttpStatusCode
){
    SERVER_ERROR("서버 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND("존재하지 않는 페이지입니다.", HttpStatus.NOT_FOUND),
    BAD_REQUEST("잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
}
