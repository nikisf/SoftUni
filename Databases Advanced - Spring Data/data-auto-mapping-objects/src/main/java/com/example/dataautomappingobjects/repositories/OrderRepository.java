package com.example.dataautomappingobjects.repositories;

import com.example.dataautomappingobjects.domain.entities.Game;
import com.example.dataautomappingobjects.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
