package com.petProject.blog.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
        String name = authentication.getName();
        String password = (String) authentication.getCredentials();

        User user = authService.login(name, password);

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> arg) {
        return true;
    }
}