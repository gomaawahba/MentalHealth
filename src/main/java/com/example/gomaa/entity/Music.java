package com.example.gomaa.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table
@Builder
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String audioUrl;
    private String imageUrl;

    public Music() {

    }

    public Music(Long id, String title, String audioUrl, String imageUrl) {
        this.id = id;
        this.title = title;
        this.audioUrl = audioUrl;
        this.imageUrl = imageUrl;
    }

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

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
