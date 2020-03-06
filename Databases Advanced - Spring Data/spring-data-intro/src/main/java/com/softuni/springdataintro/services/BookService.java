package com.softuni.springdataintro.services;

import com.softuni.springdataintro.entities.Author;
import com.softuni.springdataintro.entities.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> getAllBooksAfter2000();

    List<Book> getAllAuthorWithBookReleaseDateBefore1990();

    List<Book> findAllWithAuthorGeorgePowell();


}
