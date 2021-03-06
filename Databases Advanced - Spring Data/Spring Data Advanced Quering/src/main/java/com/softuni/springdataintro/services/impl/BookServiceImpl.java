package com.softuni.springdataintro.services.impl;

import com.softuni.springdataintro.constants.GlobalConstants;
import com.softuni.springdataintro.entities.*;
import com.softuni.springdataintro.repositories.BookRepository;
import com.softuni.springdataintro.services.AuthorService;
import com.softuni.springdataintro.services.BookService;
import com.softuni.springdataintro.services.CategoryService;
import com.softuni.springdataintro.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final FileUtil fileUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] fileContent = this.fileUtil.readFileContent(GlobalConstants.BOOKS_FILE_PATH);

        Arrays.stream(fileContent).forEach(e -> {
            String[] params = e.split("\\s+");

            Author author = this.getRandomAuthor();

            EditionType editionType = EditionType.values()[Integer.parseInt(params[0])];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate releaseDate = LocalDate.parse(params[1], formatter);
            int copies = Integer.parseInt(params[2]);
            BigDecimal price = new BigDecimal(params[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(params[4])];
            String title = this.getTitle(params);

            Set<Category> categories = this.getRandomCategories();
            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            book.setCategories(categories);
            this.bookRepository.saveAndFlush(book);
        });
    }

    @Override
    public List<Book> findAllBooksByAgeRestriction(String ageRestriction) {
        return this.bookRepository.findAllByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase()));
    }

    @Override
    public List<Book> findAllByTypeAndCopiesLessThen(String type, int copies) {
        return this.bookRepository.findAllByEditionTypeAndCopiesBefore(EditionType.valueOf(type), copies);
    }

    @Override
    public List<Book> findBooksLowerThen5HigherThen40(BigDecimal lower, BigDecimal higher) {
        return this.bookRepository.findAllByPriceBeforeOrPriceAfter(lower, higher);
    }

    @Override
    public List<Book> findAllBooksThatAreNotReleaseInYear(int year) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

        LocalDate date = LocalDate.from(formatter.parse("01/01/" + year));
        LocalDate date1 = LocalDate.from(formatter.parse("31/12/" + year));
        return this.bookRepository.findAllByReleaseDateBeforeOrReleaseDateAfter(date, date1);
    }

    @Override
    public List<Book> findAllByReleaseDateBefore(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate thisDate = LocalDate.from(formatter.parse(date));
        return this.bookRepository.findAllByReleaseDateBefore(thisDate);
    }

    @Override
    public List<Book> findAllByTitleContains(String text) {
        List<Book> books = this.bookRepository.findAllByTitleIsContaining(text);
        return books;
    }

    @Override
    public int findAllByTitleIsGreaterThan(int nws) {
        return this.bookRepository.findByTitleIsLongerThan(nws);
    }

    @Override
    public String findBookByGivenTitle(String input) {
        StringBuilder sbr = new StringBuilder();
        sbr.append(this.bookRepository.findBookByGivenTitle(input).getTitle()).append(" ")
                .append(this.bookRepository.findBookByGivenTitle(input).getEditionType()).append(" ")
                .append(this.bookRepository.findBookByGivenTitle(input).getAgeRestriction()).append(" ")
                .append(this.bookRepository.findBookByGivenTitle(input).getPrice()).append(" ");

        return sbr.toString();
    }


    private Set<Category> getRandomCategories() {
        Set<Category> result = new HashSet<>();
        Random random = new Random();
        int bound = random.nextInt(3) + 1;

        for (int i = 1; i <= bound; i++) {
            result.add(this.categoryService.getCategoryById((long) i));
        }
        return result;
    }

    private String getTitle(String[] params) {
        StringBuilder sbr = new StringBuilder();

        for (int i = 5; i < params.length; i++) {
            sbr.append(params[i]).append(" ");
        }
        return sbr.toString().trim();
    }

    private Author getRandomAuthor() {
        Random random = new Random();
        int randomId = random.nextInt(this.authorService.getAllAuthorsCount()) + 1;

        return this.authorService.findAuthorById((long) randomId);
    }
}