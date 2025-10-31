package com.petProject.blog.service;

import java.util.List;

import com.petProject.blog.api.ArticleResponse;
import com.petProject.blog.api.UpdateProfileInfoRequest;
import com.petProject.blog.api.UserProfileResponse;

public interface UserService {

    public UserProfileResponse getUserProfile(String username);
    
    public List<ArticleResponse> getUserArticles(String username);

    public UserProfileResponse updateUserProfile(UpdateProfileInfoRequest updateProfileInfo, Integer userId);

}