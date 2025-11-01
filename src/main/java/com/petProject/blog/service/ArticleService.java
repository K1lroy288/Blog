package com.petProject.blog.service;

import java.util.List;

import com.petProject.blog.api.ArticleResponse;
import com.petProject.blog.api.CommentTemplate;
import com.petProject.blog.api.CreateArticleRequest;

public interface ArticleService {
    
    public void createArticle(CreateArticleRequest createArticleRequest);

    public List<ArticleResponse> getArticles();

    public ArticleResponse getArticle(Integer articleId);

    public List<ArticleResponse> search(String search);

    public void createComment(CommentTemplate comment);

    public ArticleResponse updateComment(CommentTemplate comment);

    public ArticleResponse deleteComment(CommentTemplate comment);
}
