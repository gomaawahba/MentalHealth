package com.example.gomaa.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder

@AllArgsConstructor
@NoArgsConstructor
public class RoutineDTO {
    private String routineType;
    private List<RoutineDetailDTO> details;


}
