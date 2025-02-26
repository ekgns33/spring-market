package org.ekgns33.springmarket.product.service.port.out

import org.ekgns33.springmarket.product.domain.Seller

interface UserLoadPort {
    fun loadSeller(sellerId: Long): Seller
}