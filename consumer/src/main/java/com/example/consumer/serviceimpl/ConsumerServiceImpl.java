package com.example.consumer.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.consumer.service.BookService;
import com.example.consumer.service.ConsumerService;
import com.example.consumer.service.RatingService;
import com.example.consumer.exception.RegistroNoEncontradoException;
import com.example.consumer.model.Book;
import com.example.consumer.model.Rating;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    private final BookService bookService;
    private final RatingService ratingService;

    public ConsumerServiceImpl(BookService bookService, RatingService ratingService) {
        this.bookService = bookService;
        this.ratingService = ratingService;
    }

    @Override
    public Book findById(Long id) {

        Book book = bookService.findById(id);

        if (book.getId() == null) {
            throw new RegistroNoEncontradoException("Book not found");
        }

        List<Rating> ratingList = ratingService.findByBookId(id);

        book.setRatingList(ratingList);

        return book;

    }

}
