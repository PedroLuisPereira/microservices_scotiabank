package com.example.consumer.Service;

import com.example.consumer.model.Book;


public interface BookService {
    
    Book findById(Long id);

}
