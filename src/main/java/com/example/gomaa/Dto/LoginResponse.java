package com.example.gomaa.Dto;

public class LoginResponse {
    private Long id;
    private String token;

    public LoginResponse(Long id, String token) {
        this.id = id;
        this.token = token;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}