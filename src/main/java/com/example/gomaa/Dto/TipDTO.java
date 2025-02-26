package com.example.gomaa.Dto;

import lombok.*;


@Builder
public class TipDTO {

    private String category; // "التغذية الصحية", "الصحة النفسية والعقلية", "النظافة والعادات الصحية", "نمط الحياة الصحي"
    private String content;


    public TipDTO() {
    }

    public TipDTO(String category, String content) {
        this.category = category;
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
