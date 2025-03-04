package org.ekgns33.springmarket.product.service.port

data class ProductStockRecoverCommand(
    val productId: Long,
    val recoverAmount: Int,
) {
}
