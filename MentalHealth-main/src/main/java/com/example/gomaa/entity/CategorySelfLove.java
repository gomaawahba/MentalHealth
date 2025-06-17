package com.example.gomaa.entity;


import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table
@Builder
public class CategorySelfLove {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoryType type;

    private String content;

    public CategorySelfLove() {

    }

    public CategorySelfLove(Long id, CategoryType type, String content) {
        this.id = id;
        this.type = type;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
