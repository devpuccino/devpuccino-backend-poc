package com.devpuccino.poc.circuitbreaker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PocCircuitBreakerApplication

fun main(args: Array<String>) {
    runApplication<PocCircuitBreakerApplication>(*args)
}