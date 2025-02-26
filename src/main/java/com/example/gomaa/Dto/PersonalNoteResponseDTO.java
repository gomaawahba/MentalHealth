package com.example.gomaa.Dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class PersonalNoteResponseDTO {
    private Long id;
    private String content;
    private LocalDateTime date;
}
