package com.example.gomaa.Service;

import com.example.gomaa.Repository.SleepContentRepository;
import com.example.gomaa.entity.SleepContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SleepContentService {

    private final SleepContentRepository contentRepository;

    @Autowired
    public SleepContentService(SleepContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public List<SleepContent> getContentsByCategory(Long categoryId) {
        return contentRepository.findByCategoryId(categoryId);
    }

    public SleepContent getContentById(Long id) {
        return contentRepository.findById(id).orElseThrow(() -> new RuntimeException("المحتوى غير موجود"));
    }
}
