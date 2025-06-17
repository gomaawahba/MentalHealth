package com.example.gomaa.entity;

import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;

@Entity
@Table
@Builder
public class SunlightExposure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private int duration;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    public SunlightExposure() {

    }

    public SunlightExposure(Long id, LocalDate date, int duration, String description, Users user) {
        this.id = id;
        this.date = date;
        this.duration = duration;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
