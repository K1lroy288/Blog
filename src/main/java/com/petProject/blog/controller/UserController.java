package com.petProject.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petProject.blog.api.ArticleResponse;
import com.petProject.blog.api.UpdateProfileInfoRequest;
import com.petProject.blog.api.UserProfileResponse;
import com.petProject.blog.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping(value="/{username}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserProfile(username));
    }

    @GetMapping(value="/{username}/articles")
    public ResponseEntity<List<ArticleResponse>> getUserArticles(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserArticles(username));
    }

    @PutMapping("/{userId}/profile")
    public ResponseEntity<UserProfileResponse> updateProfile(@RequestBody UpdateProfileInfoRequest updateInforequest, @PathVariable Integer userId, Authentication authentication) {
        return ResponseEntity.ok(userService.updateUserProfile(updateInforequest, userId));    
    }

}