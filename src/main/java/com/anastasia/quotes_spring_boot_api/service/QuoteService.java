package com.anastasia.quotes_spring_boot_api.service;

import com.anastasia.quotes_spring_boot_api.dao.QuoteRepository;
import com.anastasia.quotes_spring_boot_api.entity.Quote;
import com.anastasia.quotes_spring_boot_api.rest.model.QuoteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public List<Quote> findAll() {
        return quoteRepository.findAll();
    }

    public Quote findById(int id) {
        Optional<Quote> result = quoteRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        else {
            throw new QuoteNotFoundException("Did not find quote with id = " + id);
        }
    }

    public boolean existsById(int id) {
        return quoteRepository.existsById(id);
    }

    public Quote save(Quote quote) {
        return quoteRepository.save(quote);
    }

    public void deleteById(int id) {
        quoteRepository.deleteById(id);
    }

    public List<Quote> findQuotesByText(String text) {
        return quoteRepository.findQuotesByText(text);
    }

    public Quote findRandomQuote() {
        List<Quote> quotes = findAll();
        if (quotes==null || quotes.isEmpty()) {
            throw new QuoteNotFoundException("Couldn't find any quote");
        }
        return quotes.get(new Random().nextInt(quotes.size()));
    }
}
