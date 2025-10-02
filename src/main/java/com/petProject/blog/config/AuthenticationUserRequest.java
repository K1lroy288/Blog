package com.petProject.blog.config;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthenticationUserRequest {
    private String name;
    private String password;
}