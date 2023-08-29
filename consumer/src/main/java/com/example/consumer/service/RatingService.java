package com.example.consumer.service;

import java.util.List;
import com.example.consumer.model.Rating;

public interface RatingService {
    List<Rating> findByBookId(Long id);
}
