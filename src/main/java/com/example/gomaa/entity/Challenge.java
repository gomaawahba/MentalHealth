package com.example.gomaa.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Builder
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String category; // مثل "معالجة المواقف الاجتماعية"

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    private List<ChallengeProgress> challengeProgressList = new ArrayList<>();


    public Challenge() {

    }

    public Challenge(Long id, String name, String category, List<ChallengeProgress> challengeProgressList) {
        this.id = id;
        this.name = name;

        this.category = category;
        this.challengeProgressList = challengeProgressList;
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



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ChallengeProgress> getChallengeProgressList() {
        return challengeProgressList;
    }

    public void setChallengeProgressList(List<ChallengeProgress> challengeProgressList) {
        this.challengeProgressList = challengeProgressList;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id=" + id +
                ", name='" + name + '\'' +

                ", category='" + category + '\'' +
                ", challengeProgressList=" + challengeProgressList +
                '}';
    }
}
