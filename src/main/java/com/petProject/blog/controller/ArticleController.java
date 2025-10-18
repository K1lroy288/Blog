package com.petProject.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;

import com.petProject.blog.api.ArticlesResponse;
import com.petProject.blog.api.CreateArticleRequest;
import com.petProject.blog.service.ArticleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
public class ArticleController {
    
    @Autowired
    private final ArticleService articleService;

    @PostMapping(value="", produces=APPLICATION_JSON_VALUE)
    public void createArticle(@RequestBody CreateArticleRequest createArticleRequest) {
        
        articleService.createArticle(createArticleRequest);

    }

    @GetMapping(value="")
    public ResponseEntity<List<ArticlesResponse>> getArticles() {
        
        List<ArticlesResponse> articles = articleService.getArticles();

        return ResponseEntity.ok(articles);
    }
}
