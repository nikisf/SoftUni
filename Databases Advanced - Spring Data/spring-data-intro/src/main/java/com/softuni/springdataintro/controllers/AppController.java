package com.softuni.springdataintro.controllers;

import com.softuni.springdataintro.entities.Author;
import com.softuni.springdataintro.entities.Book;
import com.softuni.springdataintro.services.AuthorService;
import com.softuni.springdataintro.services.BookService;
import com.softuni.springdataintro.services.CategoryService;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService= bookService;
    }


    @Override
    public void run(String... args) throws Exception {
        //seen data
        this.categoryService.seedCategories();
        this.authorService.seedAuthor();
        this.bookService.seedBooks();
      /* --------------------------------------------------------------------*/

        //ex1
     /*  this.bookService.getAllBooksAfter2000().forEach(e -> {
           System.out.println(e.getTitle());
       });*/

    /* --------------------------------------------------------------------*/

        //ex2

   /*     Set<String> authors = new LinkedHashSet<>();
       this.bookService.getAllAuthorWithBookReleaseDateBefore1990().forEach(e -> authors.add(e.getAuthor().getFirstName() + " " + e.getAuthor().getLastName()));
       authors.forEach(e -> System.out.println(e));*/

        /* --------------------------------------------------------------------*/

        //ex3
     /*   this.authorService.findAllAuthorsByCountOfBooks().forEach(a -> {
            System.out.println(String.format("%s %s %d", a.getFirstName(), a.getLastName(),a.getBooks().size()));
        });*/

        /* --------------------------------------------------------------------*/
        //ex4
      /*  this.bookService.findAllWithAuthorGeorgePowell().forEach(e -> System.out.println(String.format("%s %s %d", e.getTitle(),
                e.getReleaseDate(), e.getCopies())));*/

    }
}
