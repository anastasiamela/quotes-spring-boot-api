package com.anastasia.quotes_spring_boot_api.service;

import com.anastasia.quotes_spring_boot_api.dao.QuoteRepository;
import com.anastasia.quotes_spring_boot_api.model.dto.QuoteDTO;
import com.anastasia.quotes_spring_boot_api.model.entity.Quote;
import com.anastasia.quotes_spring_boot_api.model.exceptions.QuoteNotFoundException;
import com.anastasia.quotes_spring_boot_api.utils.QuoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    private static final Random RANDOM = new Random();

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public List<QuoteDTO> findAll() {
        List<Quote> quotes = quoteRepository.findAll();
        return QuoteMapper.INSTANCE.quotesToQuoteDTOs(quotes);
    }

    public QuoteDTO findById(int id) {
        return quoteRepository.findById(id)
                .map(QuoteMapper.INSTANCE::quoteToQuoteDTO)
                .orElseThrow(() -> new QuoteNotFoundException("Did not find quote with id = " + id));
    }

    public QuoteDTO create(QuoteDTO quoteDTO) {
        Quote quote = QuoteMapper.INSTANCE.quoteDTOToQuote(quoteDTO);
        Quote savedQuote = quoteRepository.save(quote);
        return QuoteMapper.INSTANCE.quoteToQuoteDTO(savedQuote);
    }

    @Transactional
    public QuoteDTO update(QuoteDTO quoteDTO) {
        Quote quote = QuoteMapper.INSTANCE.quoteDTOToQuote(quoteDTO);
        if (!quoteRepository.existsById(quote.getId())) {
            throw new QuoteNotFoundException("Quote id not found - " + quote.getId());
        }
        Quote savedQuote = quoteRepository.save(quote);
        return QuoteMapper.INSTANCE.quoteToQuoteDTO(savedQuote);
    }

    @Transactional
    public void deleteById(int id) {
        if (!quoteRepository.existsById(id)) {
            throw new QuoteNotFoundException("Quote id not found - " + id);
        }
        quoteRepository.deleteById(id);
    }

    public List<QuoteDTO> findQuotesByText(String text) {
        List<Quote> quotes = quoteRepository.findQuotesByText(text);
        return QuoteMapper.INSTANCE.quotesToQuoteDTOs(quotes);
    }

    public QuoteDTO findRandomQuote() {
        List<Quote> quotes = quoteRepository.findAll();
        if (quotes.isEmpty()) {
            throw new QuoteNotFoundException("Couldn't find any quote");
        }
        Quote randomQuote = quotes.get(RANDOM.nextInt(quotes.size()));
        return QuoteMapper.INSTANCE.quoteToQuoteDTO(randomQuote);
    }

}
