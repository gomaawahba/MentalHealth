package com.example.gomaa.Controller;

import com.example.gomaa.Service.SleepCategoryService;
import com.example.gomaa.entity.SleepCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")

public class SleepCategoryController {

    private final SleepCategoryService categoryService;

    @Autowired
    public SleepCategoryController(SleepCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<SleepCategory>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
