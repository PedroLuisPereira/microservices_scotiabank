package com.example.consumer.feign.hystrix;

import com.example.consumer.model.Rating;
import org.springframework.stereotype.Component;
import com.example.consumer.feign.ReviewsFeignClient;


import java.util.ArrayList;

import java.util.List;

/**
 * Fallback class used for feign client, in case the hystrix circuit breaks
 */
@Component
public class ReviewServiceFallback implements ReviewsFeignClient {

    @Override
    public List<Rating> getRatings(Long bookId) {
        return new ArrayList<>();
    }
}
