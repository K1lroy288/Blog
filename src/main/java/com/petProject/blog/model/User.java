package com.petProject.blog.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    @NotBlank(message="Username is required")
    @Size(max = 100, message = "Username cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "password is required")
    private String password;
    
    @ElementCollection(targetClass=Role.class, fetch=FetchType.EAGER)
    @CollectionTable(name="user_role", joinColumns=@JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @NotNull
    @Override
    public String getUsername() {
        return this.name;
    }
    
    @NotNull
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
}