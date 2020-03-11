package com.softuni.springdataintro.services;

import com.softuni.springdataintro.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthor() throws IOException;

    int getAllAuthorsCount();

    Author findAuthorById(Long id);

    List<Author> findAuthorsByLastNameStartsWith(String text);

    List<Author> findAllByFirstNameEndingWith(String text);

    List<Author> findAllAuthorsByCountOfBooks();

    List<Author> findAllAuthors();
}
