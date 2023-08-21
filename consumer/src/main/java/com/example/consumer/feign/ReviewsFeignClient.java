package com.example.consumer.feign;

import java.util.List;

import com.example.consumer.feign.hystrix.ReviewServiceFallbackFactory;
import com.example.consumer.model.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "rating", /*fallback = ReviewServiceFallback.class,*/ fallbackFactory = ReviewServiceFallbackFactory.class)
public interface ReviewsFeignClient {

    @GetMapping("/api/ratings")
    List<Rating> getMovieReviews();
}

