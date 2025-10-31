package com.petProject.blog.api;

import lombok.Data;

@Data
public class UpdateProfileInfoRequest {
    private String username;
    private String password;
    private String status;
}
