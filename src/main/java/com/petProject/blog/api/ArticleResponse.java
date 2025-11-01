package com.petProject.blog.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private String authorUsername;
    private List<CommentResponse> comments;
    
    public static ArticleResponse buildArticleResponse(Article article) {
        return new ArticleResponse()
            .setAuthorUsername(article.getAuthor().getUsername())
            .setContent(article.getContent())
            .setId(article.getId())
            .setTitle(article.getTitle())
            .setCreatedAt(article.getCreatedAt())
            .setComments(article.getComments()
                .stream()
                .map(CommentResponse::builtCommentResponse)
                .collect(Collectors.toList())
            );
    }
}