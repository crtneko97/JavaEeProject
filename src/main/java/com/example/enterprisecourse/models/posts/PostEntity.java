package com.example.enterprisecourse.models.posts;

import java.time.LocalDateTime;

import com.example.enterprisecourse.models.users.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String link;
    private String body; // New attribute for the post body
    private LocalDateTime createdAt;

    // Change createdBy to represent the owning side of the relationship
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity createdBy;

    // Constructors
    public PostEntity() {
        // Empty constructor
    }

    public PostEntity(String title, String link, String body, LocalDateTime createdAt, UserEntity createdBy) {
        this.title = title;
        this.link = link;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
