package com.example.consumer.Service;

import java.util.List;
import com.example.consumer.model.Rating;

public interface RatingService {
    List<Rating> findByBookId(Long id);
}
