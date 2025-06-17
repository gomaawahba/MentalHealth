package com.example.gomaa.Dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SunlightExposureDTO {

    private Long userId;
    private LocalDate date;
    private int duration;
}
