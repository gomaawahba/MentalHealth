package com.example.gomaa.Dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChallengeProgressDTO {
    private Long id;
    private Long userId;
    private Long challengeId;
    private boolean completed;
    private LocalDate completionDate;


}

