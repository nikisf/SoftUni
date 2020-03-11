package com.softuni.springdataintro.repositories;

import com.softuni.springdataintro.entities.AgeRestriction;
import com.softuni.springdataintro.entities.Author;
import com.softuni.springdataintro.entities.Book;
import com.softuni.springdataintro.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesBefore(EditionType editionType, int copies);

    List<Book> findAllByPriceBeforeOrPriceAfter(BigDecimal lower, BigDecimal higher);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate date, LocalDate date1);

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    List<Book> findAllByTitleIsContaining(String text);

    @Query(value = "select count(b) from Book as b where length(b.title) > :number")
    int findByTitleIsLongerThan(int number);

    @Query(value = "select b from Book as b where b.title like :input")
    Book findBookByGivenTitle(String input);

    List<Book> findAllByAuthor(Author author);


    List<Book> findAllByReleaseDateAfter(LocalDate localDate);



    @Query("SELECT b from Book as b where b.author.id = 4 order by b.releaseDate DESC, b.title ASC")
    List<Book> getAllByAuthor();
}
