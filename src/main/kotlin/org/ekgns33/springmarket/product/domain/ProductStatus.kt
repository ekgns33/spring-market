package org.ekgns33.springmarket.product.domain

enum class ProductStatus(val description: String) {
    ON_SALE("예약 및 구매 가능한 상품이 있습니다."),
    RESERVED("모든 상품이 예약중입니다."),
    OUT_OF_STOCK("모든 재고가 소진되었습니다.")
}
