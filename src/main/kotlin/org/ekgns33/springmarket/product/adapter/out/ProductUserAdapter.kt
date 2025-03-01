package org.ekgns33.springmarket.product.adapter.out

import org.ekgns33.springmarket.product.domain.Seller
import org.ekgns33.springmarket.product.service.port.out.ProductUserLoadPort
import org.ekgns33.springmarket.user.service.port.out.UserLoadPort
import org.springframework.stereotype.Component

@Component
class ProductUserAdapter(
    private val userLoadPort: UserLoadPort
) : ProductUserLoadPort {
    override fun loadSeller(sellerId: Long): Seller {
        val user = userLoadPort.findById(sellerId)
        return Seller(user.id!!, user.name)
    }
}
