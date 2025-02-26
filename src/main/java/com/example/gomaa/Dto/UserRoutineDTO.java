package com.example.gomaa.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoutineDTO {
    private String username;
    private List<RoutineDTO> routines;
}

