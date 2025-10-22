package com.petProject.blog.api;

import java.time.LocalDateTime;
import java.util.Set;

import com.petProject.blog.model.Role;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class UserProfile {
    private Integer id;
    private String username;
    private LocalDateTime createdAt;
    private Integer articlesCount;
    private Set<Role> roles;
}