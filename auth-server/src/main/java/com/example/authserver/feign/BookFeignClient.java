package com.example.authserver.feign;



import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.authserver.dto.User;


@FeignClient(name = "user-service")
public interface BookFeignClient {


    @GetMapping("")
    List<User> getBooks();

    @GetMapping("/{id}")
    User getBookById(@PathVariable("id") Long id);
}

