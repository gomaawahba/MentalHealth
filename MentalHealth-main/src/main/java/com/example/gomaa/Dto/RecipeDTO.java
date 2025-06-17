package com.example.gomaa.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {
    private String name;
    private String category; // "أطباق رئيسية", "مقبلات", "حلويات", "سناكس", "مشروبات"
    private String imageUrl;
    private String preparation;
}
