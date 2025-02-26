package com.example.gomaa.Dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseTrackingDTO {
    private Long id;
    private String exerciseType;
    private LocalDate exerciseDate;
    private int duration;
    private LocalDate date;
    private Long userId;
}

