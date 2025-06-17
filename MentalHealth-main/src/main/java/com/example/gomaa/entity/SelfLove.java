package com.example.gomaa.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table
@Builder
public class SelfLove {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategorySelfLove category;

    public SelfLove() {

    }

    public SelfLove(Long id, Users user, CategorySelfLove category) {
        this.id = id;
        this.user = user;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public CategorySelfLove getCategory() {
        return category;
    }

    public void setCategory(CategorySelfLove category) {
        this.category = category;
    }
}
