package com.devpuccino.poc.circuitbreaker.dto

data class PaymentRequest(val productId:String, val price:String, val amount:Int)