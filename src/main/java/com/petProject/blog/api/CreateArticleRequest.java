package com.petProject.blog.api;

import lombok.Data;

@Data
public class CreateArticleRequest {
    
    private String title;
    private String content;

}