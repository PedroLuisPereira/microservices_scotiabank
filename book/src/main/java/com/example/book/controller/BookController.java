package com.example.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.book.dto.BookDto;
import com.example.book.model.Book;
import com.example.book.service.BookService;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> listar() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book listarPorId(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book crear(@RequestBody BookDto bookDto) {
        return bookService.save(new Book(null, bookDto.getTitle(), bookDto.getAuthor()));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book actualizar(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return bookService.update(new Book(id, bookDto.getTitle(), bookDto.getAuthor()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        bookService.delete(id);
    }

}
