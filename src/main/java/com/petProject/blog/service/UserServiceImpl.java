package com.petProject.blog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.petProject.blog.api.ArticleResponse;
import com.petProject.blog.api.UpdateProfileInfoRequest;
import com.petProject.blog.api.UserProfileResponse;
import com.petProject.blog.model.User;
import com.petProject.blog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserProfileResponse getUserProfile(String username) {
        
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with such id not found");
        }

        User user = userOpt.get();
        UserProfileResponse userProfile = new UserProfileResponse()
            .setId(user.getId())
            .setCreatedAt(user.getCreatedAt())
            .setUsername(user.getUsername())
            .setRoles(user.getRoles())
            .setArticlesCount(user.getArticles().size())
            .setStatus(user.getStatus());
    
        return userProfile;    
    }



    @Override 
    public List<ArticleResponse> getUserArticles(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with such id not found");
        }

        User user = userOpt.get();
        return user.getArticles()
            .stream()
            .map(ArticleResponse::buildArticleResponse)
            .collect(Collectors.toList());
    }

    @Override
    public UserProfileResponse updateUserProfile(UpdateProfileInfoRequest updateProfileInfo, Integer userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with such id not found");
        }

        User user = userOpt.get();
        if(updateProfileInfo.getUsername() != null && !updateProfileInfo.getUsername().isEmpty()) {
            user.setUsername(updateProfileInfo.getUsername());
        }

        if (updateProfileInfo.getPassword() != null && !updateProfileInfo.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updateProfileInfo.getPassword()));
        }

        user.setStatus(updateProfileInfo.getStatus());
        
        userRepository.save(user);

        return getUserProfile(user.getUsername());
    }

}