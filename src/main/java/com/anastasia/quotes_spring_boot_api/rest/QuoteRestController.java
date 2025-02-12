package com.anastasia.quotes_spring_boot_api.rest;

import com.anastasia.quotes_spring_boot_api.model.dto.QuoteDTO;
import com.anastasia.quotes_spring_boot_api.service.QuoteService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuoteRestController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteRestController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public List<QuoteDTO> findAll(@RequestParam(required = false) String text) {
        if (!StringUtils.isBlank(text)) {
            return quoteService.findQuotesByText(text);
        }
        return quoteService.findAll();
    }

    @GetMapping("/{quoteId}")
    public QuoteDTO getQuoteById(@PathVariable int quoteId) {
        return quoteService.findById(quoteId);
    }

    @GetMapping("/random")
    public QuoteDTO getRandomQuote() {
        return quoteService.findRandomQuote();
    }

    @PostMapping
    public QuoteDTO addQuote(@Valid @RequestBody QuoteDTO quote) {
        return quoteService.create(quote);
    }

    @PutMapping("/{quoteId}")
    public QuoteDTO updateQuote(@PathVariable int quoteId,
                                @Valid @RequestBody QuoteDTO quote) {
        quote.setId(quoteId);
        return quoteService.update(quote);
    }

    @DeleteMapping("/{quoteId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteQuote(@PathVariable int quoteId) {
        quoteService.deleteById(quoteId);
    }

}
