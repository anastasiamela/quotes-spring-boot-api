package com.anastasia.quotes_spring_boot_api.rest.model;

public class QuoteNotFoundException extends RuntimeException{

    public QuoteNotFoundException(String message) {
        super(message);
    }
}
