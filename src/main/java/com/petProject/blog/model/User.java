package com.petProject.blog.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="users")
@Accessors(chain = true)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer Id;

    @NotBlank(message="Username is required")
    @Size(max=100, message="Username cannot exceed 100 characters")
    private String username;

    @NotBlank(message="Password is required")
    private String password;

    @ElementCollection(targetClass=Role.class, fetch=FetchType.EAGER)
    @CollectionTable(name="user_role", joinColumns=@JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Size(max=250, message="Status cannot exceed 250 characters")
    private String status;

    @OneToMany(mappedBy="author", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Article> articles;

    @OneToMany(mappedBy="author", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Article> comments;

    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @NotNull
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
}