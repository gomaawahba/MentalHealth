package com.example.gomaa.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table
@Builder
public class SleepContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // اسم القصة أو النصيحة

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description; // وصف القصة أو النصيحة

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private SleepCategory category; // ربط بالمحتوى الرئيسي

    public SleepContent() {

    }

    public SleepContent(Long id, String title, String description, SleepCategory category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SleepCategory getCategory() {
        return category;
    }

    public void setCategory(SleepCategory category) {
        this.category = category;
    }
}
