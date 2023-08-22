package com.example.consumer.feign.hystrix;

import com.example.consumer.model.Rating;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.consumer.feign.ReviewsFeignClient;


import java.util.ArrayList;
import java.util.List;

/**
 * Fallback class used for feign client, in case the hystrix circuit breaks
 * This allows access to the underlying exception that broke the circuit
 */
@Component
public class ReviewServiceFallbackFactory implements FallbackFactory<ReviewsFeignClient> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceFallbackFactory.class);

    @Override
    public ReviewsFeignClient create(Throwable throwable) {
        return new ReviewsFeignClient() {
            @Override
            public List<Rating> getRatings(@PathVariable("bookId") Long bookId) {
                LOGGER.error("Error al cargar ratings", throwable);
                return new ArrayList<>();
            }
        };
    }
}
