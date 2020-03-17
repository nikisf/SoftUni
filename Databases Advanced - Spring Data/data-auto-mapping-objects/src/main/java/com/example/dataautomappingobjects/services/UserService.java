package com.example.dataautomappingobjects.services;

import com.example.dataautomappingobjects.domain.dtos.UserLoginDto;
import com.example.dataautomappingobjects.domain.dtos.UserRegisterDto;
import com.example.dataautomappingobjects.domain.entities.User;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logout();

    boolean isLoggedUserIsAdmin();

    boolean isLogged();

    User getUser();

    User findByEmail(String mail);

    User save(User s);
}
