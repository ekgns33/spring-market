package org.ekgns33.springmarket.product.adapter.out

import org.ekgns33.springmarket.product.domain.Seller
import org.ekgns33.springmarket.product.service.port.out.UserLoadPort
import org.ekgns33.springmarket.user.service.port.`in`.UserQueryUsecase
import org.springframework.stereotype.Component

@Component
class UserInfoAdapter(
    private val userQueryUsecase: UserQueryUsecase
) : UserLoadPort {
    override fun loadSeller(sellerId: Long): Seller {
        val user = userQueryUsecase.loadUser(sellerId)
        return Seller(user.id, user.name)
    }
}