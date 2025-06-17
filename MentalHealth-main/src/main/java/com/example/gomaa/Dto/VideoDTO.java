package com.example.gomaa.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoDTO {
    private Long id;
    private String title;
    private String thumbnailUrl;
    private String videoUrl;
}
