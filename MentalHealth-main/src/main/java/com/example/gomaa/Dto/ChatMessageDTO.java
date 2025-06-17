package com.example.gomaa.Dto;


import com.example.gomaa.entity.ChatMessage;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ChatMessageDTO {
    private Long id;
    private String userMessage;
    private String aiResponse;
    private LocalDateTime timestamp;


    public ChatMessageDTO(ChatMessage chatMessage) {
        this.id = chatMessage.getId();
        this.userMessage = chatMessage.getUserMessage();
        this.aiResponse = chatMessage.getAiResponse();
        this.timestamp = chatMessage.getTimestamp();

    }

    public ChatMessageDTO(Long id, String userMessage, String aiResponse, LocalDate localDate) {
        this.id = id;
        this.userMessage = userMessage;
        this.aiResponse = aiResponse;
        this.timestamp= localDate.atStartOfDay();
    }

    // Getters
    public Long getId() { return id; }
    public String getUserMessage() { return userMessage; }
    public String getAiResponse() { return aiResponse; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
