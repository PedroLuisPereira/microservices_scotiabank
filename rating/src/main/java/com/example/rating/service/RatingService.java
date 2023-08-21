package com.example.rating.service;

import java.util.List;

import com.example.rating.model.Rating;

public interface RatingService {

    List<Rating> findAll();

    Rating findById(Long id);

    List<Rating> findByBookId(Long bookid);

    Rating save(Rating rating);

    Rating update(Rating rating);

    void delete(Long id);

}
