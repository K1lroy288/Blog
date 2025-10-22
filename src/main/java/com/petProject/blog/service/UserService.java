package com.petProject.blog.service;

import java.util.List;

import com.petProject.blog.api.ArticleResponse;
import com.petProject.blog.api.UserProfile;

public interface UserService {

    public UserProfile getUserProfile(Integer userId);

    public List<ArticleResponse> getUserArticles(Integer userId);

}