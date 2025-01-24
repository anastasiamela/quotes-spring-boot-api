package com.anastasia.quotes_spring_boot_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name="quote")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String text;

    private String author;

    public Quote(String text, String author) {
        this.text = text;
        this.author = author;
    }

    public Quote(String text) {
        this.text = text;
    }

    public Quote() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
