package org.ekgns33.springmarket.product.exceptions

import org.ekgns33.springmarket.common.exceptions.BusinessServiceException
import org.ekgns33.springmarket.common.exceptions.ErrorCode

class ProductNotFoundException(errorCode: ErrorCode) : BusinessServiceException(errorCode)
