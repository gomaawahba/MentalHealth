package com.example.gomaa.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "goal")
@Builder
public class Goal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goalTitle;  // هدف المستخدم

    private LocalDate createdDate;  // تاريخ تحديد الهدف

    private LocalTime reminderTime;  // الوقت الذي يريد المستخدم التذكير فيه

    private LocalDate targetDate;  // تاريخ تحقيق الهدف

    private boolean isCompleted;  // هل اكتمل الهدف أم لا

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;


    public Goal() {

    }

    public Goal(Long id, String goalTitle, LocalDate createdDate, LocalTime reminderTime, LocalDate targetDate, boolean isCompleted, Users user) {
        this.id = id;
        this.goalTitle = goalTitle;
        this.createdDate = createdDate;
        this.reminderTime = reminderTime;
        this.targetDate = targetDate;
        this.isCompleted = isCompleted;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoalTitle() {
        return goalTitle;
    }

    public void setGoalTitle(String goalTitle) {
        this.goalTitle = goalTitle;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(LocalTime reminderTime) {
        this.reminderTime = reminderTime;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", goalTitle='" + goalTitle + '\'' +
                ", createdDate=" + createdDate +
                ", reminderTime=" + reminderTime +
                ", targetDate=" + targetDate +
                ", isCompleted=" + isCompleted +
                ", user=" + user +
                '}';
    }
}
