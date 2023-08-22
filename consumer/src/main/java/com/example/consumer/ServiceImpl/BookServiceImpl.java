package com.example.consumer.ServiceImpl;

import org.springframework.stereotype.Service;

import com.example.consumer.Service.BookService;
import com.example.consumer.model.Book;

@Service
public class BookServiceImpl implements BookService {



    @Override
    public Book findById(Long id) {
        return null;
    }

}

