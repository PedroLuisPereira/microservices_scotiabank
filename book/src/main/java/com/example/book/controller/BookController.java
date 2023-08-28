package com.example.book.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> listar() {

        logger.info("Listar todos los books");

        return bookService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book listarPorId(@PathVariable Long id) {

        logger.info("Lista book por id:" + id);

        return bookService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book crear(@RequestBody BookDto bookDto) {

        logger.info("Crear book: " + bookDto);

        return bookService.save(new Book(null, bookDto.getTitle(), bookDto.getAuthor()));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book actualizar(@PathVariable Long id, @RequestBody BookDto bookDto) {

        logger.info("Actualizar book " + bookDto + "con id:" + id);

        return bookService.update(new Book(id, bookDto.getTitle(), bookDto.getAuthor()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {

        logger.info("Eliminar bookc con id:" + id);

        bookService.delete(id);
    }

}
