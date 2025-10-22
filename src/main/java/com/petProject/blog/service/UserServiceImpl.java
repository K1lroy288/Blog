package com.petProject.blog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.petProject.blog.api.ArticleResponse;
import com.petProject.blog.api.UserProfile;
import com.petProject.blog.model.User;
import com.petProject.blog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserProfile getUserProfile(Integer userId) {
        
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with such id not found");
        }

        User user = userOpt.get();
        UserProfile userProfile = new UserProfile()
            .setId(user.getId())
            .setCreatedAt(user.getCreatedAt())
            .setUsername(user.getUsername())
            .setRoles(user.getRoles())
            .setArticlesCount(user.getArticles().size());
    
        return userProfile;    
    }

    @Override 
    public List<ArticleResponse> getUserArticles(Integer userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with such id not found");
        }

        User user = userOpt.get();
        return user.getArticles()
            .stream()
            .map(ArticleResponse::buildArticleResponse)
            .collect(Collectors.toList());
    }

}