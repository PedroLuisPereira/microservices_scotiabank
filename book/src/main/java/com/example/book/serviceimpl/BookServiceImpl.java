package com.example.book.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import com.example.book.exception.RegistroNoEncontradoException;
import com.example.book.model.Book;
import com.example.book.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    private List<Book> list;

    public BookServiceImpl() {
        this.list = new ArrayList<Book>();
    }

    @Override
    public List<Book> findAll() {
        return list;
    }

    @Override
    public Book findById(Long id) {
        return list.stream()
          .filter(book -> Objects.equals(book.getId(), id))
          .findFirst()
          .orElseThrow(() -> new RegistroNoEncontradoException("Registro no encontrado"));
    }

    @Override
    public Book save(Book book) {
        book.setId((long) list.size() + 1);
        list.add(book);
        return book;
    }

    @Override
    public Book update(Book book) {

        int index = list.indexOf(book);
        if (index == -1) {
            throw new RegistroNoEncontradoException("Registro no encontrado");
        }
        list.set(index, book);
        return book;

    }

    @Override
    public void delete(Long id) {

        Book book = list.stream()
          .filter(book1 -> Objects.equals(book1.getId(), id))
          .findFirst()
          .orElseThrow(() -> new RegistroNoEncontradoException("Registro no encontrado"));

        list.remove(book);
    }


}
