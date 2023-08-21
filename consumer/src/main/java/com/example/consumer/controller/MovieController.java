package com.example.consumer.controller;

import com.example.consumer.model.Book;
import com.example.consumer.model.Rating;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumer.feign.ReviewsFeignClient;
import com.example.consumer.repository.Movie;

import java.util.List;


@RestController
@RequestMapping("/api/books")
public class MovieController {

    private final ReviewsFeignClient reviewsFeignClient;

    public MovieController(ReviewsFeignClient reviewsFeignClient) {
        this.reviewsFeignClient = reviewsFeignClient;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getMovie(@PathVariable("id") Long id) {

        List<Rating> ratingList = reviewsFeignClient.getMovieReviews();

        System.out.println("***********"+ratingList.size());

        return ResponseEntity.ok(new Book(1L, "title", "autor", ratingList));


    }
}
