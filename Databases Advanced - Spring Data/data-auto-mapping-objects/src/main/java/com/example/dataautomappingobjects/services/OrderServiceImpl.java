package com.example.dataautomappingobjects.services;

import com.example.dataautomappingobjects.domain.dtos.OrderAddDto;
import com.example.dataautomappingobjects.domain.entities.Game;
import com.example.dataautomappingobjects.domain.entities.Order;
import com.example.dataautomappingobjects.domain.entities.User;
import com.example.dataautomappingobjects.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final GameService gameService;
    private Set<Game> userOrders;
    private final ModelMapper modelMapper;


    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, GameService gameService, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.gameService = gameService;
        this.modelMapper = modelMapper;
        this.userOrders = new LinkedHashSet<>();
    }

    @Override
    public void AddGame(String name) {
        if (this.userService.isLogged()) {
            User user = this.userService.getUser();
            Game game = this.gameService.findGameByName(name);
            if (game != null) {
                if (this.userOrders.stream().noneMatch(e -> e.getTitle().equals(name))) {
                    this.userOrders = user.getGames();
                    userOrders.add(game);

                    System.out.printf("%s added to cart.%n", game.getTitle());
                } else {
                    System.out.println("You have this game in your shopping card");
                }
            } else {
                System.out.println("Invalid game");
            }
        } else {
            System.out.println("You are not logged in.");
        }
    }

    @Override
    public void removeGame(String name) {
        if (this.userService.isLogged()) {
            Game game = this.gameService.findGameByName(name);
            if (this.userService.isLogged()) {
                if (this.userOrders.stream().anyMatch(e -> e.getTitle().equals(name))) {
                    userOrders.removeIf(userOrder -> userOrder.getTitle().equals(name));
                    System.out.printf("%s removed from cart.", game.getTitle());
                } else {
                    System.out.println("Invalid game");
                }
            } else {
                System.out.println("You are not logged in.");
            }
        } else {
            System.out.println("You are not logged in.");
        }
    }

    @Override
    public void buyItem() {
        System.out.println("da");
        if (this.userService.isLogged()) {
            for (Game userOrder : userOrders) {
                System.out.println("dasdas");
                Game game = this.gameService.findGameByName(userOrder.getTitle());
                User user = this.userService.getUser();
                Order order = new Order();
                order.setUser(user);
                order.setGames(userOrders);
                this.orderRepository.saveAndFlush(order);
                this.gameService.save(game);
                this.userService.save(user);
            }
            System.out.println("asd");
        }else {
            System.out.println("You are not logged in.");
        }

        }
}
