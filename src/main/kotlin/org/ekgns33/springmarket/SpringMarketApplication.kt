package org.ekgns33.springmarket

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class SpringMarketApplication

fun main(args: Array<String>) {
    runApplication<SpringMarketApplication>(*args)
}
