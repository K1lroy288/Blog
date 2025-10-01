package com.petProject.blog.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.petProject.blog.config.RegistrationUserRequest;
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
    public void register(RegistrationUserRequest registrationUserRequest) {
        User user = new User()
            .setName(registrationUserRequest.getName())
            .setPassword(passwordEncoder.encode(registrationUserRequest.getPassword()))
            .setRoles(Collections.singleton(Role.USER));

        userRepository.save(user);
    }

    @NotNull
    @Override
    public User login(String name, String password) throws AuthenticationException {
        User user = userRepository.findByName(name)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Wrong password or username");
        }

        return user;
    }
}