package com.example.gomaa.Dto;

import com.example.gomaa.entity.CategoryType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategorySelfLoveDTO {
    private Long id;
    private CategoryType type;  // ARTICLE, PODCAST, QUOTE
    private String content;
}

