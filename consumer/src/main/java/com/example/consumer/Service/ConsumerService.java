package com.example.consumer.Service;

import com.example.consumer.model.Book;


public interface ConsumerService {
    
    Book findById(Long id);

}
