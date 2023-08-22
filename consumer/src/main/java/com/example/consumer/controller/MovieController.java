package com.example.consumer.controller;

import com.example.consumer.model.Book;
import com.example.consumer.model.Rating;
import com.example.consumer.repository.Movie;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumer.exception.RegistroNoEncontradoException;
import com.example.consumer.feign.BookFeignClient;
import com.example.consumer.feign.ReviewsFeignClient;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class MovieController {

    private final ReviewsFeignClient reviewsFeignClient;
    private final BookFeignClient bookFeignClient;

    public MovieController(ReviewsFeignClient reviewsFeignClient, BookFeignClient bookFeignClient) {
        this.reviewsFeignClient = reviewsFeignClient;
        this.bookFeignClient = bookFeignClient;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getMovie(@PathVariable("id") Long id) {

        Book book = bookFeignClient.getBookById(id);
        
        if(book.getId()== null){
           throw new RegistroNoEncontradoException("Book not found");
        }
        
        List<Rating> ratingList = reviewsFeignClient.getRatings(id);

        book.setRatingList(ratingList);

        return ResponseEntity.ok(book);

    }
}
