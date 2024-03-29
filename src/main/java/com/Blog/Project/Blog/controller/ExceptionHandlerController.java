package com.Blog.Project.Blog.controller;

import com.Blog.Project.Blog.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<?> generalException(GeneralException exception) {
        return new ResponseEntity<>(exception.getExceptionData(), HttpStatus.BAD_REQUEST);
    }
}
