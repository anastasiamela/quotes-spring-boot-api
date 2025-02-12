package com.anastasia.quotes_spring_boot_api.model.dto;

import jakarta.validation.constraints.NotBlank;

public class QuoteDTO {
    private Integer id;

    @NotBlank(message = "Text is mandatory")
    private String text;

    private String author;

    public QuoteDTO() {}

    public QuoteDTO(Integer id, String text, String author) {
        this.id = id;
        this.text = text;
        this.author = author;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
