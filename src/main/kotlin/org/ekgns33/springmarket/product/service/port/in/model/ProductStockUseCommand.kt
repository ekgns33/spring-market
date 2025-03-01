package org.ekgns33.springmarket.product.service.port.`in`.model


data class ProductStockUseCommand(
    val productId: Long,
    val quantity: Int,
) {

}
