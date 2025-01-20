package com.anastasia.quotes_spring_boot_api.rest.model;

public class QuoteErrorResponse {

    private int status;
    private String message;

    public QuoteErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
