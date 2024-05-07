package com.example.enterprisecourse.models.patchnotes;

import java.time.LocalDateTime;

import com.example.enterprisecourse.models.users.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PatchNotesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;
    private String version;
    private String body;
    private String patchType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity createdBy;

    // Constructors
    public PatchNotesEntity() {
        // Empty constructor
    }

    public PatchNotesEntity(LocalDateTime createdAt, String version, String body, String patchType, UserEntity createdBy) {
        this.createdAt = createdAt;
        this.version = version;
        this.body = body;
        this.patchType = patchType;
        this.createdBy = createdBy;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPatchType() {
        return patchType;
    }

    public void setPatchType(String patchType) {
        this.patchType = patchType;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }
}
