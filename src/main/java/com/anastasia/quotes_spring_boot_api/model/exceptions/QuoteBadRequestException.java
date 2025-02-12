package com.anastasia.quotes_spring_boot_api.model.exceptions;

public class QuoteBadRequestException extends RuntimeException {

    public QuoteBadRequestException(String message) {
        super(message);
    }
}
