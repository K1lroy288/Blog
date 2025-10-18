package com.petProject.blog.api;

import java.util.Set;

import com.petProject.blog.model.Role;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class LoginResponse {
    private Integer id;
    private String username;
    private String password;
    private Set<Role> roles;
}
