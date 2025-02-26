package com.example.gomaa.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Table(name = "gratitude")
@Builder
public class Gratitude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text; // User's gratitude text

    @Column(nullable = false)
    private LocalDateTime createdAt; // Timestamp

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user; // Relationship with User

    public Gratitude() {
    }

    public Gratitude(Long id, String text, LocalDateTime createdAt, Users user) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Gratitude{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                '}';
    }
}
