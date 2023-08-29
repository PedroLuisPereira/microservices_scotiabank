package com.example.consumer.service;

import com.example.consumer.model.Book;


public interface ConsumerService {
    
    Book findById(Long id);

}
