package com.example.rating.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.rating.exception.RegistroNoEncontradoException;
import com.example.rating.model.Rating;
import com.example.rating.service.RatingService;


@Service
public class RatingServiceImpl implements RatingService {

    private List<Rating> list;

    public RatingServiceImpl() {
        list = new ArrayList<Rating>();
        Rating rating = new Rating(1L, "5", 1L);
        Rating rating2 = new Rating(2L, "3", 1L);

        list.add(rating);
        list.add(rating2);
    }

    @Override
    public List<Rating> findAll() {
        return list;
    }

    @Override
    public Rating findById(Long id) {
        return list.stream()
          .filter(rating -> Objects.equals(rating.getId(), id))
          .findFirst()
          .orElseThrow(() -> new RegistroNoEncontradoException("Registro no encontrado"));
    }

    @Override
    public List<Rating> findByBookId(Long bookId) {
        return list.stream()
          .filter(rating -> Objects.equals(rating.getBookId(), bookId))
          .collect(Collectors.toList());
    }

    @Override
    public Rating save(Rating rating) {
        rating.setId((long) list.size() + 1);
        list.add(rating);
        return rating;
    }

    @Override
    public Rating update(Rating rating) {

        int index = list.indexOf(rating);
        if (index == -1) {
            throw new RegistroNoEncontradoException("Registro no encontrado");
        }
        list.set(index, rating);
        return rating;

    }

    @Override
    public void delete(Long id) {

        Rating rating = list.stream()
          .filter(rating1 -> Objects.equals(rating1.getId(), id))
          .findFirst()
          .orElseThrow(() -> new RegistroNoEncontradoException("Registro no encontrado"));

        list.remove(rating);
    }


}
