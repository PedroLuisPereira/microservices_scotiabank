package com.example.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RegistroNoEncontradoException extends RuntimeException{

    public RegistroNoEncontradoException(String msg){
        super(msg);
    }
}

