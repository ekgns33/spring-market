package org.ekgns33.springmarket.common

data class Money(
    var value: Int = 0,
) {

    fun multiply(multiplier: Int): Money {
        return Money(value * multiplier)
    }
}
