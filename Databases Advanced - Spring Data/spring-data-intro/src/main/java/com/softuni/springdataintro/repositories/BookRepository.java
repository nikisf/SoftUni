package com.softuni.springdataintro.repositories;

import com.softuni.springdataintro.entities.Author;
import com.softuni.springdataintro.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByReleaseDateAfter(LocalDate localDate);


    List<Book> findAllByReleaseDateBefore(LocalDate localDate);

    @Query("SELECT b from Book as b where b.author.id = 4 order by b.releaseDate DESC, b.title ASC")
    List<Book> getAllByAuthor();



}
