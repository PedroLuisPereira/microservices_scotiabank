package com.example.book.controller;

import com.example.book.dto.BookDto;
import com.example.book.model.Book;
import com.example.book.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BookService bookService;

    @Test
    void debeListarTodosLosBooks() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/").contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isOk())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$[0].title", is("Libro 1")));
        ;
    }

    @Test
    void debeListarUnBook() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/1").contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isOk())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.title", is("Libro 1")));
        ;
    }

    @Test
    void noDebeListarUnBook() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/1000").contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isNotFound())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.message", is("Registro no encontrado")));
        ;
    }

    @Test
    void debeCrearUnaBook() throws Exception {

        BookDto bookDto = new BookDto("Libro 3", "Autor 3");

        mockMvc.perform(MockMvcRequestBuilders.post("/")
            .content(asJsonString(bookDto))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isCreated())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.title", is("Libro 3")));
        ;
    }

    @Test
    void debeActualizarUnBook() throws Exception {

        Book book = new Book(null, "Libro 3", "Autor 3");

        book = bookService.save(book);

        BookDto bookDto = new BookDto("Libro 4", "Autor 4");

        mockMvc.perform(MockMvcRequestBuilders.put("/" + book.getId())
            .content(asJsonString(bookDto))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isOk())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.title", is("Libro 4")));
        ;
    }

    @Test
    void debeEliminarUnBook() throws Exception {

        Book book = new Book(null, "Libro 3", "Autor 3");
        book = bookService.save(book);

        mockMvc.perform(MockMvcRequestBuilders.delete("/" + book.getId())
            .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isNoContent())
        ;
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
