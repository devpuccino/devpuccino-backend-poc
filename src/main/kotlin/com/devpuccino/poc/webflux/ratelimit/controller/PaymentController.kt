package com.devpuccino.poc.webflux.ratelimit.controller

import com.devpuccino.poc.webflux.ratelimit.PaymentService
import com.devpuccino.poc.webflux.ratelimit.dto.CommonResponse
import com.devpuccino.poc.webflux.ratelimit.dto.PaymentRequest
import io.github.resilience4j.ratelimiter.RateLimiterRegistry
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import io.github.resilience4j.reactor.ratelimiter.operator.RateLimiterOperator
import org.apache.logging.log4j.LogManager
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api")
class PaymentController(val paymentService: PaymentService,val rateLimiterRegistry: RateLimiterRegistry) {

    companion object{
        private val logger = LogManager.getLogger(PaymentRequest::class.java)
        private const val RATE_LIMITER_PREFIX = "rate_limit_"
    }
    @PostMapping("/v1/payment")
    @RateLimiter(name="rate-limit-1")
    fun payment(@RequestBody paymentRequest: PaymentRequest): Mono<CommonResponse> {
        val rateLimiter = rateLimiterRegistry.rateLimiter("$RATE_LIMITER_PREFIX${paymentRequest.userId}")
        return this.paymentService.payment(paymentRequest).doOnSuccess {
            logger.info("Received a request for $paymentRequest")
        }.transformDeferred(RateLimiterOperator.of(rateLimiter))

    }
}