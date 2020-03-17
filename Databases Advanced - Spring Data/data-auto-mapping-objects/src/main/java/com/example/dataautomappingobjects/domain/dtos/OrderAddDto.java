package com.example.dataautomappingobjects.domain.dtos;

import com.example.dataautomappingobjects.domain.entities.Game;
import com.example.dataautomappingobjects.domain.entities.User;

import java.util.Set;

public class OrderAddDto {
    private User user;
    private Game game;

    public OrderAddDto() {
    }

    public OrderAddDto(User user, Game game) {
        this.user = user;
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
