package org.ekgns33.springmarket.product.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
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