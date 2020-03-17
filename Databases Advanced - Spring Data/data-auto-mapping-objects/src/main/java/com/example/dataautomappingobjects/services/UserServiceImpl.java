package com.example.dataautomappingobjects.services;

import com.example.dataautomappingobjects.domain.dtos.UserDto;
import com.example.dataautomappingobjects.domain.dtos.UserLoginDto;
import com.example.dataautomappingobjects.domain.dtos.UserRegisterDto;
import com.example.dataautomappingobjects.domain.entities.Role;
import com.example.dataautomappingobjects.domain.entities.User;
import com.example.dataautomappingobjects.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService     {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private UserDto userDto;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        User user = this.modelMapper.map(userRegisterDto, User.class);
        user.setRole(this.userRepository.count()==0 ? Role.Administrator : Role.User);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        User user = this.userRepository.findByEmail(userLoginDto.getEmail());

        if (user == null){
            System.out.println("Incorrect username / password");
            return;
        } else {
            this.userDto = this.modelMapper.map(user, UserDto.class);
            System.out.printf("Successfully logged in %s%n", user.getFullName());
        }

    }

    @Override
    public void logout() {
        if (this.userDto == null){
            System.out.println("Cannot log out. No user was logged in.");
        } else {
            String name = this.userDto.getFullName();
            this.userDto = null;
            System.out.printf("User %s successfully logged out%n", name);
        }

    }

    @Override
    public boolean isLoggedUserIsAdmin() {
        return this.userDto.getRole().equals(Role.Administrator);
    }

    @Override
    public boolean isLogged() {
        return this.userDto != null;
    }

    @Override
    public User getUser() {
        String email = this.userDto.getEmail();
        User user = this.userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User findByEmail(String mail) {
        return this.userRepository.findByEmail(mail);
    }

    @Override
    public User save(User s) {
        return this.userRepository.saveAndFlush(s);
    }


}
