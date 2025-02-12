package com.anastasia.quotes_spring_boot_api.utils;

import com.anastasia.quotes_spring_boot_api.model.dto.QuoteDTO;
import com.anastasia.quotes_spring_boot_api.model.entity.Quote;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface QuoteMapper {
    QuoteMapper INSTANCE = Mappers.getMapper(QuoteMapper.class);

    QuoteDTO quoteToQuoteDTO(Quote quote);
    Quote quoteDTOToQuote(QuoteDTO quoteDTO);
    List<QuoteDTO> quotesToQuoteDTOs(List<Quote> quotes);
}
