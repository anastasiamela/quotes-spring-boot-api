package com.anastasia.quotes_spring_boot_api.rest;

import com.anastasia.quotes_spring_boot_api.entity.Quote;
import com.anastasia.quotes_spring_boot_api.rest.model.QuoteBadRequestException;
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
        return quoteService.findById(quoteId);
    }

    @GetMapping("/quotes/random")
    public Quote getRandomQuote() {
        return quoteService.findRandomQuote();
    }

    @PostMapping("/quotes")
    public Quote addQuote(@RequestBody Quote quote) {
        if (StringUtils.isBlank(quote.getText())) {
            throw new QuoteBadRequestException("Quote text shouldn't be empty");
        }

        return quoteService.save(quote);
    }

    @PutMapping("/quotes/{quoteId}")
    public Quote updateQuote(@PathVariable int quoteId, @RequestBody Quote quote) {
        if (StringUtils.isBlank(quote.getText())) {
            throw new QuoteBadRequestException("Quote text shouldn't be empty");
        }
        Quote existingQuote = quoteService.findById(quoteId);
        if (existingQuote == null) {
            throw new QuoteNotFoundException("Quote id not found - " + quoteId);
        }
        // Ensure the id in the path and body match
        quote.setId(quoteId);
        return quoteService.save(quote);
    }

    @DeleteMapping("/quotes/{quoteId}")
    public Quote deleteQuote(@PathVariable int quoteId) {
        Quote quote = quoteService.findById(quoteId);
        if (quote == null) {
            throw new QuoteNotFoundException("Quote id not found - " + quoteId);
        }
        quoteService.deleteById(quoteId);
        return quote;
    }

}
