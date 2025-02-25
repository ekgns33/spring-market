package org.ekgns33.springmarket.common

open class BusinessServiceException(
    val errorCode: ErrorCode) : RuntimeException() {
}