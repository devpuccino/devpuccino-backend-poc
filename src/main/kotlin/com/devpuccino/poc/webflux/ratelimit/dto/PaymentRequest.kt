package com.devpuccino.poc.webflux.ratelimit.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class PaymentRequest(val userId: String, val productId: String, val promotionId: String)