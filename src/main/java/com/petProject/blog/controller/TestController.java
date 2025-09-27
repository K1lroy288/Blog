package com.petProject.blog.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("test/")
@AllArgsConstructor
public class TestController {
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
}