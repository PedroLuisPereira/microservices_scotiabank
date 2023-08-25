package com.example.consumer.feign;

import java.util.List;

import com.example.consumer.feign.hystrix.ReviewServiceFallbackFactory;
import com.example.consumer.model.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "rating-service", /*fallback = ReviewServiceFallback.class,*/ fallbackFactory = ReviewServiceFallbackFactory.class)
public interface RatingFeignClient {

    @GetMapping("/api/ratings/book/{bookId}")
    List<Rating> getRatings(@PathVariable("bookId") Long bookId);
}

