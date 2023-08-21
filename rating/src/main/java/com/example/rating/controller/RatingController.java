package com.example.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.rating.dto.RatingDto;
import com.example.rating.model.Rating;
import com.example.rating.service.RatingService;

@RestController
@RequestMapping("api/ratings")
@CrossOrigin(origins = "*")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Rating> listar() {
        return ratingService.findAll();
    }

    @GetMapping("/book/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Rating> listarPorBookId(@PathVariable Long id) {
        return ratingService.findByBookId(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Rating listarPorId(@PathVariable Long id) {
        return ratingService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Rating crear(@RequestBody RatingDto ratingDto) {
        return ratingService.save(new Rating(null, ratingDto.getStar(), ratingDto.getBookId()));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Rating actualizar(@PathVariable Long id, @RequestBody RatingDto ratingDto) {
        return ratingService.update(new Rating(id, ratingDto.getStar(), ratingDto.getBookId()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        ratingService.delete(id);
    }

}
