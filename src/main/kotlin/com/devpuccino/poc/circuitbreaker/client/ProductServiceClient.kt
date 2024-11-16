package com.devpuccino.poc.circuitbreaker.client

import com.devpuccino.poc.circuitbreaker.client.model.Product
import com.devpuccino.poc.circuitbreaker.exception.DataNotFoundException
import com.devpuccino.poc.circuitbreaker.exception.RequestTimeoutException
import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.time.Duration

@Component
class ProductServiceClient {
    private companion object{
        val logger: Logger = LogManager.getLogger(ProductServiceClient::class.java)
    }
    @Bulkhead(name="product-service")
    @CircuitBreaker(name="product-service", fallbackMethod = "getProductByIdFallback")
    fun getProductById(id:String): Mono<Product> {
        return Mono.error<Product?>(RequestTimeoutException()).doOnSubscribe {
            logger.info("Fetching Product with ID {}",id)
        }
    }

    @Bulkhead(name="product-service")
    @CircuitBreaker(name="product-service", fallbackMethod = "getProductByIdFallback")
    fun getProductById2(id:String): Mono<Product> {
        return Mono.just(Product(productId = id, productName = "abc-${id}")).delayElement(Duration.ofMillis(10005))
    }
    fun getProductByIdFallback(id:String, exception : DataNotFoundException):Mono<Product>{
        return Mono.error<Product?>(DataNotFoundException()).doOnSubscribe {
            logger.info("Fallback Fetching Product with ID {}",id)
        }
    }
}