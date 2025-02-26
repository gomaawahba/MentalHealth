package com.example.gomaa.Service;

import com.example.gomaa.Repository.SleepCategoryRepository;
import com.example.gomaa.entity.SleepCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SleepCategoryService {

    private final SleepCategoryRepository categoryRepository;

    @Autowired
    public SleepCategoryService(SleepCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<SleepCategory> getAllCategories() {
        return categoryRepository.findAll();
    }
}
