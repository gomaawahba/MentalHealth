package com.example.gomaa.entity;

import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;

@Entity
@Table
@Builder
public class PersonalNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    public PersonalNote() {
        this.date = LocalDateTime.now(); // Default to current timestamp
    }

    public PersonalNote(Long id, String content, LocalDateTime date, Users user) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.user = user;
    }

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
