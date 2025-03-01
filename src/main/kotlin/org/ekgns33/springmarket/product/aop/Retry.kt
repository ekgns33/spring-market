package org.ekgns33.springmarket.product.aop

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Retry(
    val maxRetries: Int = 10,
    val delay: Long = 1000,
)
