package com.example.dataautomappingobjects.services;

import com.example.dataautomappingobjects.domain.dtos.OrderAddDto;

public interface OrderService {

    void AddGame(String name);

    void removeGame(String name);

    void buyItem();


}
