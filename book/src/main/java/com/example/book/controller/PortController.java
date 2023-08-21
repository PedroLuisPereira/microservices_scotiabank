package com.example.book.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortController {

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/")
    public String echoService() {
        return "Echo Service " + "from port " + port;
    }

    @GetMapping("/{id}")
    public String echoService(@PathVariable String id) {
        return id + " from port " + port;
    }

}
