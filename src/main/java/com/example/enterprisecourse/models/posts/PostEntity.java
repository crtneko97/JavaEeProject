package com.example.enterprisecourse.models.posts;

import java.time.LocalDateTime;
import java.util.List;

import com.example.enterprisecourse.models.comments.CommentEntity;
import com.example.enterprisecourse.models.users.UserEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String language;
    private String body; // New attribute for the post body
    private LocalDateTime createdAt;

    // Change createdBy to represent the owning side of the relationship
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity createdBy;
    
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    // Constructors
    public PostEntity() {
        // Empty constructor
    }

    public PostEntity(String title, String language, String body, LocalDateTime createdAt, UserEntity createdBy) {
        this.title = title;
        this.language = language;
        this.body = body;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
}
