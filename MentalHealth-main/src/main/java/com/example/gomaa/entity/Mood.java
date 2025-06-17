package com.example.gomaa.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "mood")
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String moodType; // (مبسوط, كويس, عادي, مضايق, مش حلو, وحش جدا)
    private LocalDate date;  // تاريخ تسجيل المزاج

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
    public Mood() {
    }

    public Mood(Long id, String moodType, LocalDate date, Users user) {
        this.id = id;
        this.moodType = moodType;
        this.date = date;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMoodType() {
        return moodType;
    }

    public void setMoodType(String moodType) {
        this.moodType = moodType;
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
}
