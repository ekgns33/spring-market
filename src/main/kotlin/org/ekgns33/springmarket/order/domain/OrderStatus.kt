package org.ekgns33.springmarket.order.domain

enum class OrderStatus {
    REQUESTED,
    CONFIRMED,
    CANCELLED,
    REJECTED,
    COMPLETED;

    fun isCancelable(): Boolean {
        return this == REQUESTED
    }

    fun isUpdatableStatus(): Boolean {
        return this == REQUESTED || this == CONFIRMED
    }
}
