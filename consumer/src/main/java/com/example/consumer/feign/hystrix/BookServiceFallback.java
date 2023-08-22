package com.example.consumer.feign.hystrix;

import com.example.consumer.model.Book;
import org.springframework.stereotype.Component;
import com.example.consumer.feign.BookFeignClient;


/**
 * Fallback class used for feign client, in case the hystrix circuit breaks
 */
@Component
public class BookServiceFallback implements BookFeignClient {

    @Override
    public Book getBookById(Long id) {
       return new Book();
    }
}
