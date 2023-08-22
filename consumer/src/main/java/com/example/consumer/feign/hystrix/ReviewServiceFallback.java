package com.example.consumer.feign.hystrix;

import com.example.consumer.feign.RatingFeignClient;
import com.example.consumer.model.Rating;
import org.springframework.stereotype.Component;



import java.util.ArrayList;

import java.util.List;

/**
 * Fallback class used for feign client, in case the hystrix circuit breaks
 */
@Component
public class ReviewServiceFallback implements RatingFeignClient {

    @Override
    public List<Rating> getRatings(Long bookId) {
        return new ArrayList<>();
    }
}
