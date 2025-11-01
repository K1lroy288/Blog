package com.petProject.blog.api;

import lombok.Data;

@Data
public class CommentTemplate {
    private Integer id;
    private String content;
    private Integer articleId;
}