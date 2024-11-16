package com.devpuccino.poc.circuitbreaker.service

import com.devpuccino.poc.circuitbreaker.client.ProductServiceClient
import com.devpuccino.poc.circuitbreaker.client.TransactionServiceClient
import com.devpuccino.poc.circuitbreaker.dto.PaymentRequest
import com.devpuccino.poc.circuitbreaker.dto.PaymentResponse
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PaymentService(private val transactionServiceClient: TransactionServiceClient, private val productServiceClient: ProductServiceClient) {
    fun buyProduct(paymentRequest:PaymentRequest): Mono<PaymentResponse> {
        return productServiceClient.getProductById2(paymentRequest.productId).flatMap {
            transactionServiceClient.saveTransaction(paymentRequest)
        }
    }
}