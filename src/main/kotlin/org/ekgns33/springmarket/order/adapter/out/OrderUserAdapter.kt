package org.ekgns33.springmarket.order.adapter.out

import org.ekgns33.springmarket.order.service.dtos.Buyer
import org.ekgns33.springmarket.order.service.dtos.Seller
import org.ekgns33.springmarket.order.service.port.out.OrderUserLoadPort
import org.ekgns33.springmarket.user.service.port.out.UserLoadPort
import org.springframework.stereotype.Component

@Component
class OrderUserAdapter(
    private val userLoadPort: UserLoadPort
) : OrderUserLoadPort {
    override fun loadBuyer(buyerId: Long): Buyer {
        val userInfo = userLoadPort.findById(buyerId)
        return Buyer(userInfo.id!!, userInfo.name)
    }

    override fun loadSeller(sellerId: Long): Seller {
        val userInfo = userLoadPort.findById(sellerId)
        return Seller(userInfo.id!!, userInfo.name)
    }
}