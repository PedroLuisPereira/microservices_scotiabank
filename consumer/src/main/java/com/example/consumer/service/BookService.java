package com.example.consumer.service;

import java.util.List;

import com.example.consumer.model.Book;

public interface BookService {

    List<Book> findAll();

    Book findById(Long id);

}
