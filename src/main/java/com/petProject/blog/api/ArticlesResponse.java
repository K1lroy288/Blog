package com.petProject.blog.api;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class ArticlesResponse {
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private String authorName;
}