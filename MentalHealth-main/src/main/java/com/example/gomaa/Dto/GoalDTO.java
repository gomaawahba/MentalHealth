package com.example.gomaa.Dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoalDTO {
    private Long id;
    private String goalTitle;
    private LocalDate createdDate;
    private LocalTime reminderTime;
    private LocalDate targetDate;
    private boolean isCompleted;
    private Long userId;
}