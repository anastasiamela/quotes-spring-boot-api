package com.anastasia.quotes_spring_boot_api.rest;

import com.anastasia.quotes_spring_boot_api.entity.Quote;
import com.anastasia.quotes_spring_boot_api.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuoteRestController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteRestController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/quotes")
    public List<Quote> findAll() {
        return quoteService.findAll();
    }

    @GetMapping("/quotes/{quoteId}")
    public Quote getQuoteById(@PathVariable int quoteId) {
        Quote quote = quoteService.findById(quoteId);
        if (quote == null) {
            throw new RuntimeException("Quote id not found - " + quoteId);
        }
        return quote;
    }

    @PostMapping("/quotes")
    public Quote addQuote(@RequestBody Quote quote) {
        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        quote.setId(0);
        return quoteService.save(quote);
    }

    @PutMapping("/quotes")
    public Quote updateQuote(@RequestBody Quote quote) {
        return quoteService.save(quote);
    }

    @DeleteMapping("/quote/{quoteId}")
    public Quote deleteQuote(@PathVariable int quoteId) {
        Quote quote = quoteService.findById(quoteId);
        if (quote == null) {
            throw new RuntimeException("Quote id not found - " + quoteId);
        }
        quoteService.deleteById(quoteId);
        return quote;
    }
}
