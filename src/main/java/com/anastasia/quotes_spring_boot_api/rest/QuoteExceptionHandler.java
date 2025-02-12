package com.anastasia.quotes_spring_boot_api.rest;

import com.anastasia.quotes_spring_boot_api.model.exceptions.QuoteBadRequestException;
import com.anastasia.quotes_spring_boot_api.model.dto.QuoteErrorResponse;
import com.anastasia.quotes_spring_boot_api.model.exceptions.QuoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
