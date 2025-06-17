package com.example.gomaa.Controller;

import com.example.gomaa.Dto.SleepContentDTO;
import com.example.gomaa.Service.SleepContentService;
import com.example.gomaa.entity.SleepContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contents")

public class SleepContentController {

    private final SleepContentService contentService;

    @Autowired
    public SleepContentController(SleepContentService contentService) {
        this.contentService = contentService;
    }

    // جلب جميع المحتويات الخاصة بفئة معينة مثل "قصص قبل النوم"
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<SleepContent>> getContentsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(contentService.getContentsByCategory(categoryId));
    }
    //another api to get id, title and description
    @GetMapping("/category2/{categoryId}")
    public ResponseEntity<List<SleepContentDTO>> getContentsByCategory2(@PathVariable Long categoryId) {
        List<SleepContent> contents = contentService.getContentsByCategory(categoryId);

        // تحويل SleepContent إلى SleepContentDTO
        List<SleepContentDTO> contentDTOs = contents.stream()
                .map(content -> new SleepContentDTO(content.getId(), content.getTitle(), content.getDescription()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(contentDTOs);
    }

    // Return only description
    @GetMapping("/{contentId}")
    public ResponseEntity<String> getContentDescriptionById(@PathVariable Long contentId) {
        SleepContent content = contentService.getContentById(contentId);
        return ResponseEntity.ok(content.getDescription()); // Return only description
    }
}
