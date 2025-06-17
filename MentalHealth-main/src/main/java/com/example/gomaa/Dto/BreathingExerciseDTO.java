package com.example.gomaa.Dto;

import lombok.Builder;


import lombok.*;

@Data
@NoArgsConstructor

@Builder
public class BreathingExerciseDTO {

    private Long id;
    private String exerciseName;
    private String exerciseDescription;
    private String exerciseInstructions;
    private String videoUrl;
    private Long userId;

    // ✅ Add this public constructor explicitly
    public BreathingExerciseDTO(Long id, String exerciseName, String exerciseDescription,
                                String exerciseInstructions, String videoUrl, Long userId) {
        this.id = id;
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.exerciseInstructions = exerciseInstructions;
        this.videoUrl = videoUrl;
        this.userId = userId;
    }
}

