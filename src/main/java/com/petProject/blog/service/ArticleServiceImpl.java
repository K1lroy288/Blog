package com.petProject.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.petProject.blog.api.ArticlesResponse;
import com.petProject.blog.api.CreateArticleRequest;
import com.petProject.blog.repository.ArticleRepository;
import com.petProject.blog.model.Article;
import com.petProject.blog.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    
    @Autowired
    ArticleRepository articleRepository;

    @Override
    public void createArticle(CreateArticleRequest createArticleRequest) {
    
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authenticated");
        }
        
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof User)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid user");
        }

        User author = (User) principal;

        Article article = new Article()
            .setAuthor(author)
            .setContent(createArticleRequest.getContent())
            .setTitle(createArticleRequest.getTitle());
            
        articleRepository.save(article);
    }

    @Override
    public List<ArticlesResponse> getArticles() {
        return articleRepository.findAll()
            .stream()
            .map(this::buildArticlesResponse)
            .collect(Collectors.toList());
    }

    @Override
    public ArticlesResponse buildArticlesResponse(Article article) {
        return new ArticlesResponse()
            .setAuthorName(article.getAuthor().getUsername())
            .setDescription(article.getContent())
            .setId(article.getId())
            .setTitle(article.getTitle())
            .setCreatedAt(article.getCreatedAt());
    }

}
