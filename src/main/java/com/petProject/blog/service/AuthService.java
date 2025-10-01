package com.petProject.blog.service;

import com.petProject.blog.model.User;
import com.petProject.blog.config.RegistrationUserRequest;

import jakarta.validation.constraints.NotNull;

public interface AuthService {
    @NotNull
    void register(RegistrationUserRequest registrationUserRequest);
    
    @NotNull
    User login(String name, String password);
}