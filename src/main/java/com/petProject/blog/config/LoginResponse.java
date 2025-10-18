package com.petProject.blog.config;

import java.util.Set;

import com.petProject.blog.model.Role;

import lombok.Data;

@Data
public class LoginResponse {
    private Integer id;
    private String username;
    private String password;
    private Set<Role> roles;
}
