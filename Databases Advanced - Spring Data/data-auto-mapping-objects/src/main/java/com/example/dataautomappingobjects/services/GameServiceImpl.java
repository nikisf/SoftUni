package com.example.dataautomappingobjects.services;

import com.example.dataautomappingobjects.domain.dtos.GameAddDto;
import com.example.dataautomappingobjects.domain.entities.Game;
import com.example.dataautomappingobjects.domain.entities.User;
import com.example.dataautomappingobjects.repositories.GameRepository;
import com.example.dataautomappingobjects.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserRepository userRepository, UserService userService, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;

        this.userService = userService;
        this.modelMapper = modelMapper;

    }

    @Override
    public void addGame(GameAddDto gameAddDto) {
        if (this.userService.isLogged()) {
            if (this.userService.isLoggedUserIsAdmin()) {
                Game game = this.modelMapper.map(gameAddDto, Game.class);
                User user = this.userService.findByEmail(this.userService.getUser().getEmail());
                Set<Game> userGames = user.getGames();
                userGames.add(game);
                user.setGames(userGames);
                this.gameRepository.saveAndFlush(game);
                this.userService.save(user);
                System.out.println(String.format("Game %s was added successfully", game.getTitle()));
            } else {
                System.out.println("You are not admin");
            }
        } else {
            System.out.println("You are not logged in");
        }
    }

    @Override
    public void editGame(Long id, String[] props) {
        if (this.userService.isLogged()) {
            if (this.userService.isLoggedUserIsAdmin()) {
                Game game = this.gameRepository.findById(id).orElse(null);
                if (game == null) {
                    System.out.println("Invalid game id");
                } else {
                    for (String prop : props) {
                        String[] input = prop.split("=");
                        switch (input[0]) {
                            case "title":
                                game.setTitle(input[1]);
                                break;
                            case "price":
                                game.setPrice(new BigDecimal(input[1]));
                                break;
                            case "size":
                                game.setSize(Double.parseDouble(input[1]));
                                break;
                            case "trailer":
                                game.setTrailer(input[1]);
                                break;
                            case "thumbnailURL":
                                game.setThumbnailURL(input[1]);
                                break;
                            case "description":
                                game.setDescription(input[1]);
                                break;
                            case "releaseDate":
                                game.setReleaseDate(LocalDate.parse(input[1], DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                                break;
                        }
                    }
                    this.gameRepository.saveAndFlush(game);
                    System.out.printf("Game %s was edited successfully%n", game.getTitle());
                }
            } else {
                System.out.println("You are not admin");
            }
        } else {
            System.out.println("You are not logged in");
        }
    }

    @Override
    public void deleteGame(Long id) {
        if (this.userService.isLogged()) {
            if (this.userService.isLoggedUserIsAdmin()) {
                Game game = this.gameRepository.findById(id).orElse(null);
                if (game == null) {
                    System.out.println("Invalid game id");
                } else {
                    this.gameRepository.delete(game);
                    System.out.printf("Game %s was deleted successfully", game.getTitle());
                }
            } else {
                System.out.println("You are not admin");
            }
        } else {
            System.out.println("You are not logged in");
        }
    }

    @Override
    public List<Game> showAllGames() {
        return this.gameRepository.findAll();
    }

    @Override
    public void printGames() {
        if (this.userService.isLogged()) {
            showAllGames().forEach(e -> System.out.println(String.format("%s %.2f", e.getTitle(), e.getPrice())));
        } else {
            System.out.println("You are not logged in");
        }
    }

    @Override
    public void detailGame(String name) {
        Game game = this.gameRepository.findGameByTitle(name);
        if (game != null) {
            System.out.println("Title: " + game.getTitle());
            System.out.println("Price: " + game.getPrice());
            System.out.println("Description: " + game.getDescription());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
            System.out.println("Release date: " + formatter.format(game.getReleaseDate()));
        } else {
            System.out.println("Invalid game name");
        }
    }

    @Override
    public void ownedGames() {
        User user = this.userService.findByEmail(this.userService.getUser().getEmail());
        user.getGames().forEach(e -> System.out.println(e.getTitle()));
    }

    @Override
    public Game findGameByName(String name) {
        return this.gameRepository.findGameByTitle(name);
    }

    @Override
    public Game save(Game s) {
        return this.gameRepository.saveAndFlush(s);
    }


}
