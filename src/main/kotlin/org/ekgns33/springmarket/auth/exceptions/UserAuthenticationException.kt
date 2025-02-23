package org.ekgns33.springmarket.auth.exceptions

import org.ekgns33.springmarket.common.ErrorCode

class UserAuthenticationException(
    val errorCode: ErrorCode,
) : RuntimeException()
