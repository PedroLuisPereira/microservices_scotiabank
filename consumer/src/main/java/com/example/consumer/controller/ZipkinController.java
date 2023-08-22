package com.example.consumer.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

@RestController
class ZipkinController {

    @Autowired
    RestTemplate restTemplate;

    private static final Logger LOG = Logger.getLogger(ZipkinController.class.getName());

    @GetMapping(value = "/zipkin")
    public String zipkinService1() {
        LOG.info("Inside zipkinService 1..");

        restTemplate.exchange("http://localhost:8082/api/books", HttpMethod.GET, null,
                new ParameterizedTypeReference<String>() {
                }).getBody();

        return "Hi...";
    }
}