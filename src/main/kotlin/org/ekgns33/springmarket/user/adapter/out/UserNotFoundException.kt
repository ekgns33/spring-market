package org.ekgns33.springmarket.user.adapter.out

import org.ekgns33.springmarket.common.exceptions.BusinessServiceException
import org.ekgns33.springmarket.common.exceptions.ErrorCode

class UserNotFoundException(errorCode: ErrorCode) : BusinessServiceException(errorCode)
