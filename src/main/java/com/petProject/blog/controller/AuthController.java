package com.petProject.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petProject.blog.config.AuthenticationUserRequest;
import com.petProject.blog.config.LoginResponse;
import com.petProject.blog.model.User;
import com.petProject.blog.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    
    @Autowired
    private final AuthService authService;

    @PostMapping(value="/registration", produces=APPLICATION_JSON_VALUE)
    public void registration(@RequestBody AuthenticationUserRequest authenticationUserRequest) {
        authService.register(authenticationUserRequest);
    }

    @PostMapping(value="/login", produces=APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@RequestBody AuthenticationUserRequest authenticationUserRequest) {
        User user = authService.login(authenticationUserRequest);

        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(user.getId());
        loginResponse.setPassword(user.getPassword());
        loginResponse.setUsername(user.getUsername());
        loginResponse.setRoles(user.getRoles());

        return ResponseEntity.ok(loginResponse);
    }

}