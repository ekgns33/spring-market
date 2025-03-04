package org.ekgns33.springmarket.order.adapter.out

import org.ekgns33.springmarket.order.domain.OrderLine
import org.ekgns33.springmarket.order.service.port.out.ProductCommandPort
import org.ekgns33.springmarket.product.service.port.ProductStockRecoverCommand
import org.ekgns33.springmarket.product.service.port.`in`.ProductUpdateUsecase
import org.ekgns33.springmarket.product.service.port.`in`.model.ProductStockUseCommand
import org.springframework.stereotype.Component

@Component
class ProductUpdateAdapter(
    private val productUpdateUsecase: ProductUpdateUsecase
) : ProductCommandPort {

    override fun useStock(orderLine: OrderLine) {
        productUpdateUsecase.useStockForReservation(
            ProductStockUseCommand(
                productId = orderLine.productId,
                quantity = orderLine.quantity
            )
        )
    }

    override fun cancelReservation(orderLine: OrderLine) {
        productUpdateUsecase.recoverStockForReservation(
            ProductStockRecoverCommand(
                productId = orderLine.productId,
                recoverAmount = orderLine.quantity
            )
        )
    }
}