package com.petProject.blog.api;

import lombok.Data;

@Data
public class AuthenticationUserRequest {
    private String username;
    private String password;
}