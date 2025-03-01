package org.ekgns33.springmarket.order.service.port.out

import org.ekgns33.springmarket.order.service.dtos.Buyer
import org.ekgns33.springmarket.order.service.dtos.Seller

interface OrderUserLoadPort {
    fun loadBuyer(buyerId: Long): Buyer
    fun loadSeller(sellerId: Long): Seller
}
