package com.petProject.blog.api;

import java.time.LocalDateTime;

import com.petProject.blog.model.Article;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class ArticleResponse {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String authorName;

    public static ArticleResponse buildArticleResponse(Article article) {
        return new ArticleResponse()
            .setAuthorName(article.getAuthor().getUsername())
            .setContent(article.getContent())
            .setId(article.getId())
            .setTitle(article.getTitle())
            .setCreatedAt(article.getCreatedAt());
    }
}