package com.example.gomaa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "breathing_exercise_videos")
public class BreathingExerciseVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String imageUrl;

    private String videoUrl;

    public BreathingExerciseVideo() {
    }

    public BreathingExerciseVideo(Long id, String name, String imageUrl, String videoUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
