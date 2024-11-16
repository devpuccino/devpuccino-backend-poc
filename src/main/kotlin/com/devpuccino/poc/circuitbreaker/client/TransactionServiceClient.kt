package com.devpuccino.poc.circuitbreaker.client

import com.devpuccino.poc.circuitbreaker.dto.PaymentRequest
import com.devpuccino.poc.circuitbreaker.dto.PaymentResponse
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.UUID

@Component
class TransactionServiceClient {
    private companion object{
        val logger = LogManager.getLogger(TransactionServiceClient::class.java)
    }
    fun saveTransaction(paymentRequest:PaymentRequest):Mono<PaymentResponse>{
        logger.info("Saving transaction {}",paymentRequest)
        return Mono.just(PaymentResponse(transactionId = UUID.randomUUID().toString()))
    }
}