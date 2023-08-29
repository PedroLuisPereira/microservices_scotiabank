package com.example.consumer.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.consumer.service.RatingService;
import com.example.consumer.feign.RatingFeignClient;
import com.example.consumer.model.Rating;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingFeignClient ratingFeignClient;

    public RatingServiceImpl(RatingFeignClient ratingFeignClient) {
        this.ratingFeignClient = ratingFeignClient;
    }

    @Override
    public List<Rating> findByBookId(Long id) {
        return ratingFeignClient.getRatings(id);
    }

}

