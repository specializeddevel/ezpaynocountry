package com.ezpay.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorManager {

    private final View error;

    public ErrorManager(View error) {
        this.error = error;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity treatError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity treatError400() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity ValidationException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AccountNotFoundException.class)
    public HashMap<String, String> AccountNotFoundException(AccountNotFoundException e) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("error", e.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public HashMap<String, String> UserNotFoundException(UserNotFoundException e) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("error", e.getMessage());
        return errors;
    }


}