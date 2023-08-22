package com.example.consumer.controller;

import com.example.consumer.model.Book;
import com.example.consumer.model.Rating;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumer.exception.RegistroNoEncontradoException;
import com.example.consumer.feign.BookFeignClient;
import com.example.consumer.feign.RatingFeignClient;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final RatingFeignClient ratingFeignClient;
    private final BookFeignClient bookFeignClient;

    public BookController(RatingFeignClient ratingFeignClient, BookFeignClient bookFeignClient) {
        this.ratingFeignClient = ratingFeignClient;
        this.bookFeignClient = bookFeignClient;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id) {

        Book book = bookFeignClient.getBookById(id);
        
        if(book.getId()== null){
           throw new RegistroNoEncontradoException("Book not found");
        }
        
        List<Rating> ratingList = ratingFeignClient.getRatings(id);

        book.setRatingList(ratingList);

        return ResponseEntity.ok(book);

    }
}
