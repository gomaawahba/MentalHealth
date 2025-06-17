package com.example.gomaa.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;

@Entity
@Table(name = "breathing_exercises")
@Builder
public class BreathingExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String exerciseName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String exerciseDescription;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String exerciseInstructions;

    @Column(length = 255)
    private String videoUrl;

    //اضافة data عشان ترجع التاريخ في الجدول الزمني
    @Column(nullable = false, columnDefinition = "DATE DEFAULT '1970-01-01'")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    public BreathingExercise() {
    }

    public BreathingExercise(Long id, String exerciseName, String exerciseDescription, String exerciseInstructions, String videoUrl, Users user) {
        this.id = id;
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.exerciseInstructions = exerciseInstructions;
        this.videoUrl = videoUrl;
        this.user = user;
    }

    //all faileds
    public BreathingExercise(Long id, String exerciseName, String exerciseDescription, String exerciseInstructions, String videoUrl, LocalDate date, Users user) {
        this.id = id;
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.exerciseInstructions = exerciseInstructions;
        this.videoUrl = videoUrl;
        this.date = date;
        this.user = user;
    }
    //----------------------------------------getter and setter LocalDate ------------------------

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
//--------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public String getExerciseInstructions() {
        return exerciseInstructions;
    }

    public void setExerciseInstructions(String exerciseInstructions) {
        this.exerciseInstructions = exerciseInstructions;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BreathingExercise{" +
                "id=" + id +
                ", exerciseName='" + exerciseName + '\'' +
                ", exerciseDescription='" + exerciseDescription + '\'' +
                ", exerciseInstructions='" + exerciseInstructions + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", user=" + user +
                '}';
    }
}
