package com.example.consumer.controller;

import com.example.consumer.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumer.Service.ConsumerService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final ConsumerService consumerService;

    public BookController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id) {

        Book book = consumerService.findById(id);

        return ResponseEntity.ok(book);

    }
}
