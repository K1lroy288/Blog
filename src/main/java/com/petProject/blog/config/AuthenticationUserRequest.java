package com.petProject.blog.config;

import lombok.Data;

@Data
public class AuthenticationUserRequest {
    private String username;
    private String password;
}