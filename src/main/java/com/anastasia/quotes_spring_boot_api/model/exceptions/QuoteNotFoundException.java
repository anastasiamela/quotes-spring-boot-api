package com.anastasia.quotes_spring_boot_api.model.exceptions;

public class QuoteNotFoundException extends RuntimeException{

    public QuoteNotFoundException(String message) {
        super(message);
    }
}
