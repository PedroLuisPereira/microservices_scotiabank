package com.example.consumer.feign;


import com.example.consumer.feign.hystrix.BookServiceFallback;
import com.example.consumer.model.Book;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "book-service", fallback = BookServiceFallback.class)
public interface BookFeignClient {


    @GetMapping("")
    List<Book> getBooks();

    @GetMapping("/{id}")
    Book getBookById(@PathVariable("id") Long id);
}

