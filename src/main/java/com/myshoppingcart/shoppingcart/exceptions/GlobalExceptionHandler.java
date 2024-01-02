package com.myshoppingcart.shoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ItemNotFoundException.class, CartNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(RuntimeException ex) {
        ErrorResponse errorResponse = ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCartItemQuantityException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCartItemQuantityException(InvalidCartItemQuantityException ex) {
        ErrorResponse errorResponse = ErrorResponse.create(ex, HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.create(ex, HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
