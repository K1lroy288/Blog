package com.petProject.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.petProject.blog.api.ArticleResponse;
import com.petProject.blog.api.CommentTemplate;
import com.petProject.blog.api.CreateArticleRequest;
import com.petProject.blog.api.SearchRequest;
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
    public ResponseEntity<List<ArticleResponse>> getArticles() {
        List<ArticleResponse> articles = articleService.getArticles();
        return ResponseEntity.ok(articles);
    }

    @GetMapping(value="/{articleId}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable Integer articleId) {
        return ResponseEntity.ok(articleService.getArticle(articleId));
    }

    @PostMapping(value="/search")
    public ResponseEntity<List<ArticleResponse>> search(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(articleService.search(request.getQuery()));
    }

    @PostMapping(value="/comment")
    public void createComment(@RequestBody CommentTemplate comment) {
        articleService.createComment(comment);
    }

    @PatchMapping(value="/comment")
    public ResponseEntity<ArticleResponse> updateComment(@RequestBody CommentTemplate comment) {
        return ResponseEntity.ok(articleService.updateComment(comment));
    }

    @DeleteMapping(value="/comment")
    public ResponseEntity<ArticleResponse> ddeleteComment(@RequestBody CommentTemplate comment) {
        return ResponseEntity.ok(articleService.deleteComment(comment));
    }
}
