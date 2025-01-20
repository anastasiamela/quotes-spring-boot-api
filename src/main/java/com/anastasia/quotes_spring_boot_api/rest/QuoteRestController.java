package com.anastasia.quotes_spring_boot_api.rest;

import com.anastasia.quotes_spring_boot_api.entity.Quote;
import com.anastasia.quotes_spring_boot_api.rest.model.QuoteNotFoundException;
import com.anastasia.quotes_spring_boot_api.service.QuoteService;
import io.micrometer.common.util.StringUtils;
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
    public List<Quote> findAll(@RequestParam(required = false) String text) {
        if (!StringUtils.isBlank(text)) {
            return quoteService.findQuotesByText(text);
        }
        return quoteService.findAll();
    }

    @GetMapping("/quotes/{quoteId}")
    public Quote getQuoteById(@PathVariable int quoteId) {
        Quote quote = quoteService.findById(quoteId);
        if (quote == null) {
            throw new QuoteNotFoundException("Quote id not found - " + quoteId);
        }
        return quote;
    }

    @GetMapping("/quotes/random")
    public Quote getRandomQuote() {
        return quoteService.findRandomQuote();
    }

    @PostMapping("/quotes")
    public Quote addQuote(@RequestBody Quote quote) {
        return quoteService.save(quote);
    }

    @PutMapping("/quotes")
    public Quote updateQuote(@RequestBody Quote quote) {
        if (!quoteService.existsById(quote.getId())) {
            throw new QuoteNotFoundException("Quote id not found - " + quote.getId());
        }
        return quoteService.save(quote);
    }

    @DeleteMapping("/quote/{quoteId}")
    public Quote deleteQuote(@PathVariable int quoteId) {
        Quote quote = quoteService.findById(quoteId);
        if (quote == null) {
            throw new QuoteNotFoundException("Quote id not found - " + quoteId);
        }
        quoteService.deleteById(quoteId);
        return quote;
    }

}
