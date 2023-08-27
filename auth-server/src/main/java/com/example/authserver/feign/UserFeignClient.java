package com.example.authserver.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.authserver.dto.Usuario;



@FeignClient(name = "user-service")
public interface UserFeignClient {

    @GetMapping("/username/{username}")
    Usuario getUserByUsername(@PathVariable("username") String username);
}
