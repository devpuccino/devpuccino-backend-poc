package com.devpuccino.poc.webflux.ratelimit.handler

import com.devpuccino.poc.webflux.ratelimit.dto.CommonResponse
import io.github.resilience4j.ratelimiter.RequestNotPermitted
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(RequestNotPermitted::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    fun handleRequestNotPermitted(exception:RequestNotPermitted):CommonResponse{
        return CommonResponse("429-001","Too many requests")
    }
}