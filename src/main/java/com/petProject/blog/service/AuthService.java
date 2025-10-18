package com.petProject.blog.service;

import com.petProject.blog.config.AuthenticationUserRequest;
import com.petProject.blog.model.User;

public interface AuthService {
    public void register(AuthenticationUserRequest authenticationUserRequest);

    public User login(AuthenticationUserRequest authenticationUserRequest);
}