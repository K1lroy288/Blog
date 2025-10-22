package com.petProject.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petProject.blog.api.ArticleResponse;
import com.petProject.blog.api.UserProfile;
import com.petProject.blog.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping(value="/{userId}")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @GetMapping(value="/{userId}/articles")
    public ResponseEntity<List<ArticleResponse>> getUserArticles(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserArticles(userId));
    }

}