package com.example.enterprisecourse.models.comments;

import com.example.enterprisecourse.models.posts.PostEntity;
import com.example.enterprisecourse.models.users.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private LocalDateTime createdAt;

    @ManyToOne
    private UserEntity createdBy;

    @ManyToOne
    private PostEntity post;

    // Constructors
    public CommentEntity() {
        // Default constructor
    }

    public CommentEntity(String content, LocalDateTime createdAt, UserEntity createdBy, PostEntity post) {
        this.content = content;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.post = post;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }
}
