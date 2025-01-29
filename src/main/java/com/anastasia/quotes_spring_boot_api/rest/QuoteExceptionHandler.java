package com.anastasia.quotes_spring_boot_api.rest;

import com.anastasia.quotes_spring_boot_api.rest.model.QuoteBadRequestException;
import com.anastasia.quotes_spring_boot_api.rest.model.QuoteErrorResponse;
import com.anastasia.quotes_spring_boot_api.rest.model.QuoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class QuoteExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(QuoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public QuoteErrorResponse handleException(QuoteNotFoundException exception) {
        return new QuoteErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(QuoteBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public QuoteErrorResponse handleException(QuoteBadRequestException exception) {
        return new QuoteErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}
