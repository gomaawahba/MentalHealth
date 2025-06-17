package com.example.gomaa.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GratitudeDTO {
    private Long id;
    private String text;
    private LocalDateTime createdAt;
    private Long userId; // User ID
}
