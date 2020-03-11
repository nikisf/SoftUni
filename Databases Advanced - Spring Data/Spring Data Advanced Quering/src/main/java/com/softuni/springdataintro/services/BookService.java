package com.softuni.springdataintro.services;

import com.softuni.springdataintro.entities.Book;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksByAgeRestriction(String ageRestriction);

    List<Book> findAllByTypeAndCopiesLessThen(String type, int copies);

    List<Book> findBooksLowerThen5HigherThen40(BigDecimal lower, BigDecimal higher);

    List<Book> findAllBooksThatAreNotReleaseInYear(int date);

    List<Book> findAllByReleaseDateBefore(String date);

    List<Book> findAllByTitleContains(String text);

    int findAllByTitleIsGreaterThan(int nws);

    String findBookByGivenTitle(String input);


}
