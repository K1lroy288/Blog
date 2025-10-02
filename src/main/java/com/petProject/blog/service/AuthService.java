package com.petProject.blog.service;

import com.petProject.blog.model.User;
import com.petProject.blog.config.AuthenticationUserRequest;

import jakarta.validation.constraints.NotNull;

public interface AuthService {
    @NotNull
    void register(AuthenticationUserRequest registrationUserRequest);
    
    @NotNull
    User login(AuthenticationUserRequest authenticationUserRequest);
}