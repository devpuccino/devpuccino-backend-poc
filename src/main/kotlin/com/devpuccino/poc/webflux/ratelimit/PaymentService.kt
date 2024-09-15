package com.devpuccino.poc.webflux.ratelimit

import com.devpuccino.poc.webflux.ratelimit.dto.CommonResponse
import com.devpuccino.poc.webflux.ratelimit.dto.PaymentRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PaymentService {
    fun payment(request:PaymentRequest):Mono<CommonResponse>{
        return Mono.just(CommonResponse("200-000","Success."))
    }
}