package org.ekgns33.springmarket.product.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.core.Ordered.LOWEST_PRECEDENCE
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Aspect
@Component
@Order(LOWEST_PRECEDENCE-1)
class RetryAspect {

    @Around("@annotation(retryAnnotation)")
    fun retryAdvice(joinPoint: ProceedingJoinPoint, retryAnnotation: Retry): Any? {
        var attempts = 0
        val maxRetries = retryAnnotation.maxRetries
        val delay = retryAnnotation.delay
        while (true) {
            try {
                return joinPoint.proceed()
            } catch (ex: Exception) {
                attempts++
                if (attempts >= maxRetries) {
                    throw ex
                }
                Thread.sleep(delay)
            }
        }
    }
}