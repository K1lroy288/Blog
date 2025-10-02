package com.petProject.blog.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.petProject.blog.config.AuthenticationUserRequest;
import com.petProject.blog.model.Role;
import com.petProject.blog.model.User;
import com.petProject.blog.repository.UserRepository;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @NotNull
    @Override
    public void register(AuthenticationUserRequest registrationUserRequest) {
        User user = new User()
            .setName(registrationUserRequest.getName())
            .setPassword(passwordEncoder.encode(registrationUserRequest.getPassword()))
            .setRoles(Collections.singleton(Role.USER));

        userRepository.save(user);
    }

    @NotNull
    @Override
    public User login(AuthenticationUserRequest authenticationUserRequest) {
        Optional<User> userOpt = userRepository.findByName(authenticationUserRequest.getName());
        
        if (userOpt.isEmpty()) {
            return null;
        }

        User user = userOpt.get();
        if (!passwordEncoder.matches(authenticationUserRequest.getPassword(), user.getPassword())) {
            return null;
        }

        return user;
    }
}