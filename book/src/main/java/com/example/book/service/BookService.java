package com.example.book.service;

import java.util.List;


import com.example.book.model.Book;

public interface BookService {
    
    List<Book> findAll();

    Book findById(Long id);

    Book save(Book book);

    Book update(Book book);

    void delete(Long id);

}
