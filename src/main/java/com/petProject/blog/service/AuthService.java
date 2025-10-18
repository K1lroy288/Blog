package com.petProject.blog.service;

import com.petProject.blog.api.AuthenticationUserRequest;
import com.petProject.blog.model.User;

public interface AuthService {
    public void register(AuthenticationUserRequest authenticationUserRequest);

    public User login(AuthenticationUserRequest authenticationUserRequest);
}