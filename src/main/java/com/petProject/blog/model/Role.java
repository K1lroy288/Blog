package com.petProject.blog.model;

import org.springframework.security.core.GrantedAuthority;

import jakarta.validation.constraints.NotNull;

public enum Role implements GrantedAuthority {
    USER;

    @NotNull
    @Override
    public String getAuthority() {
        return name();
    }
}