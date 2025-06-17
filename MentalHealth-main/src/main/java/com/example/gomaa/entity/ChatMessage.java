package com.example.gomaa.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000) // Increase size for longer responses
    private String userMessage;

    @Column(nullable = false, columnDefinition = "TEXT") // Use TEXT for large responses
    private String aiResponse;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public ChatMessage() {
        this.timestamp = LocalDateTime.now();
    }

    public ChatMessage(String userMessage, String aiResponse, Users user) {
        this.userMessage = userMessage;
        this.aiResponse = aiResponse;
        this.user = user;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getAiResponse() {
        return aiResponse;
    }

    public void setAiResponse(String aiResponse) {
        this.aiResponse = aiResponse;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}

