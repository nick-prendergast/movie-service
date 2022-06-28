package com.example.movieservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        final StringBuilder errors = new StringBuilder();
        e.getConstraintViolations().forEach(x -> errors.append(x.getInvalidValue()).append(" - ").append(x.getMessage()));
        return new ResponseEntity<>("not valid due to :" + errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final StringBuilder errors = new StringBuilder();
        for (final FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.append(error.getField()).append(": ").append(error.getDefaultMessage());
        }
        return new ResponseEntity<>("not valid due to validation error: " + errors, HttpStatus.BAD_REQUEST);
    }

}
