package com.petProject.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petProject.blog.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {}