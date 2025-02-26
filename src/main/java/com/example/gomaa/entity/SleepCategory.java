package com.example.gomaa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Entity
@Table
@Builder
public class SleepCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // اسم الفئة (قصص قبل النوم - نصائح لتحسين النوم - التوازن - الاسترخاء)

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SleepContent> contents;

    public SleepCategory() {

    }

    public SleepCategory(Long id, String name, List<SleepContent> contents) {
        this.id = id;
        this.name = name;
        this.contents = contents;
    }

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

    public List<SleepContent> getContents() {
        return contents;
    }

    public void setContents(List<SleepContent> contents) {
        this.contents = contents;
    }
}
