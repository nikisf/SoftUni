package com.example.dataautomappingobjects.controllers;

import com.example.dataautomappingobjects.domain.dtos.GameAddDto;
import com.example.dataautomappingobjects.domain.dtos.UserLoginDto;
import com.example.dataautomappingobjects.domain.dtos.UserRegisterDto;
import com.example.dataautomappingobjects.services.GameService;
import com.example.dataautomappingobjects.services.OrderService;
import com.example.dataautomappingobjects.services.UserService;
import com.example.dataautomappingobjects.utils.ValidationUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.io.BufferedReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Component
public class AppController implements CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final GameService gameService;
    private final OrderService orderService;

    public AppController(BufferedReader bufferedReader, ValidationUtil validationUtil, UserService userService, GameService gameService, OrderService orderService) {
        this.bufferedReader = bufferedReader;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.gameService = gameService;
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) throws Exception {

        while(true){
            System.out.println("");
            System.out.println("Enter input: ");
            String[] input = this.bufferedReader.readLine().split("\\|");

            if (input[0].equals("RegisterUser")){
                if (!input[2].equals(input[3])){
                    System.out.println("Passwords don't match");
                    return;
                }
                UserRegisterDto userRegisterDto = new UserRegisterDto(input[1], input[2], input[4]);

                if (this.validationUtil.isValid(userRegisterDto)){
                    this.userService.registerUser(userRegisterDto);
                    System.out.printf("%s was registered!%n", input[4]);
                } else {
                    this.validationUtil.getViolation(userRegisterDto).stream().map(ConstraintViolation::getMessage)
                            .forEach(System.out::println);
                }

            } else if (input[0].equals("LoginUser")){
                UserLoginDto user = new UserLoginDto(input[1], input[2]);
                this.userService.loginUser(user);
            } else if (input[0].equals("Logout")){
                this.userService.logout();
            } else if (input[0].equals("AddGame")){
                GameAddDto gameAddDto = new GameAddDto(
                    input[1], new BigDecimal(input[2]), Double.parseDouble(input[3]),
                        input[4],input[5], input[6], LocalDate.parse(input[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                );

                if(this.validationUtil.isValid(gameAddDto)){
                    this.gameService.addGame(gameAddDto);
                } else {
                    this.validationUtil.getViolation(gameAddDto).stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
                }
                System.out.println();
            } else if (input[0].equals("EditGame")){
                String[] props = Arrays.copyOfRange(input, 2, input.length);
                Long id = Long.parseLong(input[1]);
                this.gameService.editGame(id, props);
            } else if (input[0].equals("DeleteGame")){
                Long id = Long.parseLong(input[1]);
                this.gameService.deleteGame(id);
            } else if (input[0].equals("AllGames")) {
                this.gameService.printGames();
            } else if (input[0].equals("DetailGame")){
                this.gameService.detailGame(input[1]);
            } else if (input[0].equals("OwnedGames")){
                this.gameService.ownedGames();
            } else if (input[0].equals("AddItem")){
                this.orderService.AddGame(input[1]);
            } else if (input[0].equals("RemoveItem")){
                this.orderService.removeGame(input[1]);
            } else if (input[0].equals("BuyItem")){
                this.orderService.buyItem();
            }
        }
        
    }
}
