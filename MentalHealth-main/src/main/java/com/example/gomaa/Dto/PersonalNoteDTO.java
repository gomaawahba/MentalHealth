package com.example.gomaa.Dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class PersonalNoteDTO {
    private Long id;
    private String content;
    private LocalDateTime date;
}
