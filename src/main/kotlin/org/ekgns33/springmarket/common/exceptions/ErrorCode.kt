package org.ekgns33.springmarket.common.exceptions

import org.springframework.http.HttpStatusCode

interface ErrorCode {
    val description: String
    val responseCode: HttpStatusCode
}