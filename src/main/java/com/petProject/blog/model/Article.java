package com.petProject.blog.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="articles", uniqueConstraints=@UniqueConstraint(columnNames={"author_id", "title"}))
@Accessors(chain=true)
public class Article {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message="Title is required")
    @Size(max=250, message="Title canot exceed 250 character")
    private String title;

    @NotBlank(message="Content is required")
    @Column(columnDefinition="TEXT")
    private String content;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="author_id", nullable=false)
    private User author;

    @OneToMany(mappedBy="article", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Comment> comments;

    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

}
