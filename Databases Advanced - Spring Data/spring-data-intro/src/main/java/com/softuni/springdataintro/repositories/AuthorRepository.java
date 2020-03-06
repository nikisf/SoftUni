package com.softuni.springdataintro.repositories;

import com.softuni.springdataintro.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a from Author as a order by a.books.size DESC")
    List<Author> findAuthorByCountOfBooks();






}
