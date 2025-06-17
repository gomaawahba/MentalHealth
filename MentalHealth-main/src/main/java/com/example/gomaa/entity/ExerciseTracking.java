package com.example.gomaa.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;

@Entity
@Table
@Builder
public class ExerciseTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String exerciseType; // Exercise Type (VARCHAR 255)

    private LocalDate exerciseDate; // Exercise Date (DATE)

    private int duration; // Duration (INT)

    private LocalDate date; // Entry Date (DATE) when the record was added

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;


    public ExerciseTracking() {

    }

    public ExerciseTracking(Long id, String exerciseType, LocalDate exerciseDate, int duration, LocalDate date, Users user) {
        this.id = id;
        this.exerciseType = exerciseType;
        this.exerciseDate = exerciseDate;
        this.duration = duration;
        this.date = date;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public LocalDate getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(LocalDate exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ExerciseTracking{" +
                "id=" + id +
                ", exerciseType='" + exerciseType + '\'' +
                ", exerciseDate=" + exerciseDate +
                ", duration=" + duration +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
