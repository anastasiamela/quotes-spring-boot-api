package com.anastasia.quotes_spring_boot_api.rest;

import com.anastasia.quotes_spring_boot_api.model.dto.QuoteDTO;
import com.anastasia.quotes_spring_boot_api.model.exceptions.QuoteNotFoundException;
import com.anastasia.quotes_spring_boot_api.service.QuoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuoteRestController.class)
class QuoteRestControllerTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc to test REST APIs

    @MockitoBean
    private QuoteService quoteService; // Mock the QuoteService to avoid calling real service logic

    @Test
    void testCreateQuote_HappyPath() throws Exception {
        String requestBody = """
        {
            "author": "Oscar Wilde",
            "text": "Be yourself; everyone else is already taken."
        }
        """;

        QuoteDTO newQuote = new QuoteDTO(1,"Be yourself; everyone else is already taken.", "Oscar Wilde");
        when(quoteService.create(any(QuoteDTO.class))).thenReturn(newQuote);


        mockMvc.perform(post("/api/quotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value("Oscar Wilde"))
                .andExpect(jsonPath("$.text").value("Be yourself; everyone else is already taken."));

    }

    @Test
    void testAddQuote_UnhappyPath() throws Exception {
        String requestBody = """
        {
            "author": "Oscar Wilde"
        }
        """;

        // When: A POST request is performed
        mockMvc.perform(post("/api/quotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Quote text shouldn't be empty"));
    }

    @Test
    void testGetQuoteById_HappyPath() throws Exception {
        // Given: A quote exists
        QuoteDTO quote = new QuoteDTO(1, "Be yourself; everyone else is already taken.", "Oscar Wilde");
        when(quoteService.findById(1)).thenReturn(quote);

        // When: The GET request is performed
        mockMvc.perform(get("/api/quotes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.author").value("Oscar Wilde"))
                .andExpect(jsonPath("$.text").value("Be yourself; everyone else is already taken."));
    }

    @Test
    void testGetQuoteById_UnhappyPath() throws Exception {
        // Given: No quote exists
        when(quoteService.findById(2)).thenThrow(new QuoteNotFoundException("Quote id not found - 2"));

        // When: The GET request is performed
        mockMvc.perform(get("/api/quotes/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Quote id not found - 2"));
    }
}

