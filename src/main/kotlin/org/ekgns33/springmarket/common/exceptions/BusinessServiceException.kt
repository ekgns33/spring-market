package org.ekgns33.springmarket.common.exceptions

open class BusinessServiceException(
    val errorCode: ErrorCode
) : RuntimeException() {
}