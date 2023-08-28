package com.example.authserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authserver.dto.Usuario;
import com.example.authserver.feign.UserFeignClient;

@RestController
public class BookController {

    private final UserFeignClient userFeignClient;

    public BookController(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }


    @GetMapping("/usuarios")
    public Usuario getBooks() {
        return userFeignClient.getUserByUsername("user@email.com");
    }
}
