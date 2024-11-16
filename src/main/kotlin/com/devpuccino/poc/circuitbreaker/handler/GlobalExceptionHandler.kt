package com.devpuccino.poc.circuitbreaker.handler

import com.devpuccino.poc.circuitbreaker.exception.DataNotFoundException
import com.devpuccino.poc.circuitbreaker.exception.RequestTimeoutException
import io.github.resilience4j.circuitbreaker.CallNotPermittedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException::class)
    fun handleDataNotFoundException():ResponseEntity<Any>{
        return ResponseEntity.badRequest().body("Data not found")
    }

    @ExceptionHandler(CallNotPermittedException::class)
    fun handleCallNotPermittedException(exception: CallNotPermittedException):ResponseEntity<Any>{
        return ResponseEntity.internalServerError().body("circuit breaker ${exception.causingCircuitBreakerName} is open")
    }

    @ExceptionHandler(RequestTimeoutException::class)
    fun handleRequestTimeoutException(exception: RequestTimeoutException):ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout")
    }
}