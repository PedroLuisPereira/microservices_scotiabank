package com.example.consumer.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.consumer.Service.BookService;
import com.example.consumer.Service.RatingService;
import com.example.consumer.model.Book;
import com.example.consumer.model.Rating;

@Service
public class RatingServiceImpl implements RatingService {

    @Override
    public List<Rating> findByBookId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByBookId'");
    }

}

