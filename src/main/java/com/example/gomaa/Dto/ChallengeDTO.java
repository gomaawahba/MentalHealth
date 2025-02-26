package com.example.gomaa.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChallengeDTO {
    private Long id;
    private String name;
    private String category;

}
