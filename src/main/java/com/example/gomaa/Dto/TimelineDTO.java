package com.example.gomaa.Dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimelineDTO {
    private LocalDate date;
    private String description;
}
