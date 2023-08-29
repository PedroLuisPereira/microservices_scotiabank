package com.example.rating.controller;

import com.example.rating.dto.RatingDto;
import com.example.rating.model.Rating;
import com.example.rating.service.RatingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class RatingControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    RatingService ratingService;

    @Test
    void debeListarTodosLosRatings() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/").contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isOk())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$[0].stars", is("5")));
        ;
    }

    @Test
    void debeListarUnRating() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/1").contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isOk())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.stars", is("5")));
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
    void debeCrearUnRating() throws Exception {

        RatingDto ratingDto = new RatingDto("3", 1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/")
            .content(asJsonString(ratingDto))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isCreated())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.stars", is("3")));
        ;
    }

    @Test
    void debeActualizarUnRating() throws Exception {

        Rating rating = new Rating(null, "4", 1L);

        rating = ratingService.save(rating);

        RatingDto ratingDto = new RatingDto("3", 1L);

        mockMvc.perform(MockMvcRequestBuilders.put("/" + rating.getId())
            .content(asJsonString(ratingDto))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isOk())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.stars", is("3")));
        ;
    }

    @Test
    void debeEliminarUnRating() throws Exception {

        Rating rating = new Rating(null, "4", 1L);

        rating = ratingService.save(rating);

        mockMvc.perform(MockMvcRequestBuilders.delete("/" + rating.getId())
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


