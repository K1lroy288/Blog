package com.petProject.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petProject.blog.config.RegistrationUserRequest;
import com.petProject.blog.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("test/")
@RequiredArgsConstructor
public class TestController {
    
    @Autowired
    private final AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/welcome")
    public String welcome() { return "this is unprotected page"; }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String pageForUser() { return "This is page for only users"; }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String pageForAdmins() { return "This is page for admin"; }

    @GetMapping("/all")
    public String pageForAll() { return "This is page for all employees"; }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    
    @PostMapping(value="/registration", produces=APPLICATION_JSON_VALUE)
    public void registration(@RequestBody RegistrationUserRequest registrationUserRequest) {
        authService.register(registrationUserRequest);
    }
}