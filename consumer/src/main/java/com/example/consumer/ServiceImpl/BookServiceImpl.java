package com.example.consumer.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.consumer.Service.BookService;
import com.example.consumer.feign.BookFeignClient;
import com.example.consumer.model.Book;

@Service
public class BookServiceImpl implements BookService {

    
    private final BookFeignClient bookFeignClient;


    public BookServiceImpl(BookFeignClient bookFeignClient) {
        this.bookFeignClient = bookFeignClient;
    }


    @Override
    public Book findById(Long id) {
        return bookFeignClient.getBookById(id);
    }


    @Override
    public List<Book> findAll() {
        return bookFeignClient.getBooks();
    }

}

