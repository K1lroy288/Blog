/* package com.petProject.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.petProject.blog.model.User;
import com.petProject.blog.service.AuthService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private final AuthService authService;

    @Override
    public Authentication authenticate(Authentication authentication) {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        AuthenticationUserRequest authenticationUserRequest = new AuthenticationUserRequest();
        authenticationUserRequest.setUsername(username);
        authenticationUserRequest.setPassword(password);

        User user = authService.login(authenticationUserRequest);

        return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> arg) {
        return true;
    }

} */