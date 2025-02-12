package com.anastasia.quotes_spring_boot_api.dao;

import com.anastasia.quotes_spring_boot_api.model.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Integer> {

    @Query("select q from Quote q where q.text like %:text%")
    List<Quote> findQuotesByText(String text);

}
