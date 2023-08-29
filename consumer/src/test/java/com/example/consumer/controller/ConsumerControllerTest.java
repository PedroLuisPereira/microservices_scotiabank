package com.example.consumer.controller;

import com.example.consumer.service.BookService;
import com.example.consumer.service.RatingService;
import com.example.consumer.exception.RegistroNoEncontradoException;
import com.example.consumer.model.Book;
import com.example.consumer.model.Rating;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;


import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
class ConsumerControllerTest {

    @MockBean
    BookService bookService;

    @MockBean
    RatingService ratingService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void debeListarBookConSusRatings() throws Exception {

        when(bookService.findById(1L)).thenReturn(new Book(1L, "Libro 1", "Autor 1"));

        when(ratingService.findByBookId(1L)).thenReturn(Arrays.asList(
          new Rating(1L, "5", 1L),
          new Rating(2L, "4", 1L)
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/1").contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isOk())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.title", is("Libro 1")))
          .andExpect(jsonPath("$.author", is("Autor 1")))
          .andExpect(jsonPath("$['ratingList'][0]['stars']", is("5")))
          .andExpect(jsonPath("$['ratingList'][0]['bookId']", is(1)));
    }
}
