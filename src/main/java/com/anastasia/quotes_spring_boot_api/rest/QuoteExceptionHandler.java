package com.anastasia.quotes_spring_boot_api.rest;

import com.anastasia.quotes_spring_boot_api.rest.model.QuoteErrorResponse;
import com.anastasia.quotes_spring_boot_api.rest.model.QuoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class QuoteExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<QuoteErrorResponse> handleException(QuoteNotFoundException exception) {
        QuoteErrorResponse error = new QuoteErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
