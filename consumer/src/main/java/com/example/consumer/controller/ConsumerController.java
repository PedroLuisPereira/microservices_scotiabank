package com.example.consumer.controller;

import com.example.consumer.model.Book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumer.service.ConsumerService;

@RestController
public class ConsumerController {

    private Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    private final ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id) {

        logger.info("Consultar book con id: {}", id);

        Book book = consumerService.findById(id);

        logger.info("Consulta de book con sus ratings {}", book);

        return ResponseEntity.ok(book);

    }
}
