package com.example.dataautomappingobjects.services;

import com.example.dataautomappingobjects.domain.dtos.GameAddDto;
import com.example.dataautomappingobjects.domain.entities.Game;
import com.example.dataautomappingobjects.domain.entities.User;

import java.util.List;

public interface GameService {

    void addGame(GameAddDto gameAddDto);

    void editGame(Long id, String[] props);

    void deleteGame(Long id);

    List<Game> showAllGames();

    void printGames();

    void detailGame(String name);

    void ownedGames();

    Game findGameByName(String name);

    Game save(Game s);
}
