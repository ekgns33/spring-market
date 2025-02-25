package org.ekgns33.springmarket.product.exceptions

import org.ekgns33.springmarket.common.BusinessServiceException
import org.ekgns33.springmarket.common.ErrorCode

class ProductNotFoundException(errorCode: ErrorCode) : BusinessServiceException(errorCode)
