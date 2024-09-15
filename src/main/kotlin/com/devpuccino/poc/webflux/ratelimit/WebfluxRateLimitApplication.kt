package com.devpuccino.poc.webflux.ratelimit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebfluxRateLimitApplication

fun main(args: Array<String>) {
    runApplication<WebfluxRateLimitApplication>(*args)
}