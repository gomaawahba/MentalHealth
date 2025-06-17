package com.example.gomaa.Dto;

import com.example.gomaa.entity.Mood;
import com.example.gomaa.entity.Users;

import java.time.LocalDate;

public class MoodHistoryResponse {
    private Long id;
    private String moodType;
    private LocalDate date;

    public MoodHistoryResponse(Mood mood) {
        this.id = mood.getId();
        this.moodType = mood.getMoodType();
        this.date = mood.getDate();
    }

    public Long getId() {
        return id;
    }

    public String getMoodType() {
        return moodType;
    }

    public LocalDate getDate() {
        return date;
    }
}
