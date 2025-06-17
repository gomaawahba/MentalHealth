package com.example.gomaa.Dto;

public class LoginResponse {
    private Long id;
    private String accessToken;
    private String refreshToken;

    public LoginResponse(Long id, String accessToken, String refreshToken) {
        this.id = id;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
