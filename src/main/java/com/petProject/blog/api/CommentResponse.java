package com.petProject.blog.api;

import java.time.LocalDateTime;

import com.petProject.blog.model.Comment;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class CommentResponse {
    private Integer id;
    private String content;
    private String authorUsername;
    private LocalDateTime createdAt;

    public static CommentResponse builtCommentResponse(Comment comment) {
        return new CommentResponse()
            .setAuthorUsername(comment.getAuthor().getUsername())
            .setId(comment.getId())
            .setContent(comment.getContent())
            .setCreatedAt(comment.getCreatedAt());
    }
}