package com.todo.rails.elite.starter.code.exception;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// TODO 15: Add Global Exception Handling. Use @ControllerAdvice and @ExceptionHandler to handle exceptions like EntityNotFoundException.
@ControllerAdvice
public class Globalexceptionhandling {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFoundExceptionHandler(EntityNotFoundException entityNotFoundException) {
        return ResponseEntity.badRequest().body(entityNotFoundException.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runTimeExceptionHandler(RuntimeException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}

