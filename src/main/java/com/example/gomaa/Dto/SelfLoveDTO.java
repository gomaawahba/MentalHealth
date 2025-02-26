package com.example.gomaa.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelfLoveDTO {
    private Long id;
    private Long userId;
    private CategorySelfLoveDTO categorySelfLove;
}

