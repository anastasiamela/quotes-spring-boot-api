package com.anastasia.quotes_spring_boot_api.rest.model;

public class QuoteBadRequestException extends RuntimeException{

    public QuoteBadRequestException(String message) {
        super(message);
    }
}
