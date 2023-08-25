package com.example.rating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortController {

    @Autowired
    private Environment env;

    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    public String port() {
        return "Port @Value =  " + port + " Port Environment = " + env.getProperty("local.server.port");
    }

}