package com.softuni.springdataintro.controllers;

import com.softuni.springdataintro.entities.Book;
import com.softuni.springdataintro.services.AuthorService;
import com.softuni.springdataintro.services.BookService;
import com.softuni.springdataintro.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.Year;

@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService= bookService;
        this.bufferedReader = bufferedReader;
    }


    @Override
    public void run(String... args) throws Exception {
        //seed data
        this.categoryService.seedCategories();
        this.authorService.seedAuthor();
        this.bookService.seedBooks();
        /* --------------------------------------------------------------------*/
        //ex1
        /*System.out.print("Enter age restriction: ");
      this.bookService.findAllBooksByAgeRestriction(this.bufferedReader.readLine())
        .forEach(e -> System.out.println(e.getTitle()));*/

        /* --------------------------------------------------------------------*/
        //ex2
        /*this.bookService.findAllByTypeAndCopiesLessThen("GOLD", 5000)
        .forEach(e -> System.out.println(e.getTitle()));*/

        /* --------------------------------------------------------------------*/
        //ex3
      /*  this.bookService.findBooksLowerThen5HigherThen40(new BigDecimal(5), new BigDecimal(40))
                .forEach(e -> System.out.println(String.format("%s - %.2f", e.getTitle(), e.getPrice())));*/

        /* --------------------------------------------------------------------*/
        //ex4
/*        System.out.print("Enter a Year: ");
        int year = Integer.parseInt(this.bufferedReader.readLine());
        this.bookService.findAllBooksThatAreNotReleaseInYear(year).forEach(e -> System.out.println(e.getTitle()));*/

        /* --------------------------------------------------------------------*/
        //ex5
       /* System.out.print("Enter a date: ");
        this.bookService.findAllByReleaseDateBefore(this.bufferedReader.readLine())
                .forEach(e -> System.out.println(String.format("%s %s %.2f", e.getTitle(), e.getEditionType().name(),e.getPrice())));
*/

        /* --------------------------------------------------------------------*/
        //ex6
       /* System.out.print("Enter String: ");
        this.authorService.findAllByFirstNameEndingWith(this.bufferedReader.readLine())
        .forEach(e -> System.out.println(String.format("%s %s", e.getFirstName(), e.getLastName())));*/

        /* --------------------------------------------------------------------*/
        //ex7
    /*    System.out.print("Enter String: ");
        this.bookService.findAllByTitleContains(this.bufferedReader.readLine())
                .forEach(e -> System.out.println(e.getTitle()));*/

        /* --------------------------------------------------------------------*/
        //ex8
  /*      System.out.print("Enter string: ");
        this.authorService.findAuthorsByLastNameStartsWith(this.bufferedReader.readLine())
                .forEach(e -> e.getBooks().
                        forEach(c -> System.out.println(String.format("%s  (%s %s)", c.getTitle(), e.getFirstName(), e.getLastName()))));*/

        /* --------------------------------------------------------------------*/
        //ex9
  /*      System.out.println("Enter a number: ");
        System.out.println(this.bookService.findAllByTitleIsGreaterThan(Integer.parseInt(this.bufferedReader.readLine())));*/

        /* --------------------------------------------------------------------*/
        //ex10
        /*this.authorService.findAllAuthors().forEach(e->
                System.out.println(String.format("%s %s - %d", e.getFirstName(), e.getLastName(), e.getBooks().stream().mapToInt(Book::getCopies).sum())));*/

        /* --------------------------------------------------------------------*/
        //ex11
        System.out.print("Enter a title: ");
        System.out.println(this.bookService.findBookByGivenTitle(this.bufferedReader.readLine()));


    }
}
