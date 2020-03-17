package com.example.dataautomappingobjects.repositories;

import com.example.dataautomappingobjects.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Game findGameByTitle(String title);
}
