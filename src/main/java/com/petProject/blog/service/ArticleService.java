package com.petProject.blog.service;

import java.util.List;

import com.petProject.blog.api.ArticlesResponse;
import com.petProject.blog.api.CreateArticleRequest;
import com.petProject.blog.model.Article;

public interface ArticleService {
    
    public void createArticle(CreateArticleRequest createArticleRequest);

    public List<ArticlesResponse> getArticles();

    public ArticlesResponse buildArticlesResponse(Article article);

}
