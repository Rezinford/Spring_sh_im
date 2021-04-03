package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UseExistException extends RuntimeException {
    public UseExistException(String message) {
        super(message);
    }
}
