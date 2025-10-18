package com.petProject.blog.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.petProject.blog.config.AuthenticationUserRequest;
import com.petProject.blog.model.Role;
import com.petProject.blog.model.User;
import com.petProject.blog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(AuthenticationUserRequest authenticationUserRequest) {
        
        Optional<User> userOpt =  userRepository.findByUsername(authenticationUserRequest.getUsername());
        if (!userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with such username already exists");
        }
        
        User user = new User()
            .setUsername(authenticationUserRequest.getUsername())
            .setPassword(passwordEncoder.encode(authenticationUserRequest.getPassword()))
            .setRoles(Collections.singleton(Role.USER));
        
        userRepository.save(user);
    }

    @Override
    public User login(AuthenticationUserRequest authenticationUserRequest) {
        
        Optional<User> userOpt = userRepository.findByUsername(authenticationUserRequest.getUsername());
        if(userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Wrong login or password");
        }

        User user = userOpt.get();
        if (!passwordEncoder.matches(authenticationUserRequest.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Wrong login or password");
        }

        return user;
    }

}