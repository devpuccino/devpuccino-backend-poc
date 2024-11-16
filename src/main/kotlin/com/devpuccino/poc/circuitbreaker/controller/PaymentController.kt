package com.devpuccino.poc.circuitbreaker.controller

import com.devpuccino.poc.circuitbreaker.dto.PaymentRequest
import com.devpuccino.poc.circuitbreaker.dto.PaymentResponse
import com.devpuccino.poc.circuitbreaker.service.PaymentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1/payment")
class PaymentController(private val paymentService: PaymentService) {
    @PostMapping
    fun buyProduct(@RequestBody paymentRequest: PaymentRequest): Mono<PaymentResponse> {
        return paymentService.buyProduct(paymentRequest)
    }
}