package com.devpuccino.poc.webflux.ratelimit.config

import io.github.resilience4j.ratelimiter.RateLimiterConfig
import io.github.resilience4j.ratelimiter.RateLimiterRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class RateLimitConfig {
    @Bean
    fun rateLimiterConfig(): RateLimiterConfig {
        return RateLimiterConfig.custom().limitForPeriod(10)
            .limitRefreshPeriod(Duration.ofSeconds(1))
            .timeoutDuration(Duration.ZERO).build()
    }
    @Bean
    fun rateLimiterRegistry(rateLimiterConfig: RateLimiterConfig): RateLimiterRegistry {
        return RateLimiterRegistry.custom().withRateLimiterConfig(rateLimiterConfig).build()
    }
}