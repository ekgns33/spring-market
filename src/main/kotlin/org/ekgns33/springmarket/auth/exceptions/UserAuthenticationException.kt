package org.ekgns33.springmarket.auth.exceptions

import org.ekgns33.springmarket.common.GlobalErrorCode

class UserAuthenticationException(
    val errorCode: GlobalErrorCode,
) : RuntimeException()
