package com.example.gomaa.Controller;

import com.example.gomaa.Dto.CategorySelfLoveDTO;
import com.example.gomaa.Service.CategorySelfLoveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category-selflove")
public class CategorySelfLoveController {

    private final CategorySelfLoveService categorySelfLoveService;

    public CategorySelfLoveController(CategorySelfLoveService categorySelfLoveService) {
        this.categorySelfLoveService = categorySelfLoveService;
    }

    @PostMapping
    public CategorySelfLoveDTO addCategory(@RequestBody CategorySelfLoveDTO dto) {
        return categorySelfLoveService.addCategory(dto);
    }

    @GetMapping
    public List<CategorySelfLoveDTO> getAllCategories() {
        return categorySelfLoveService.getAllCategories();
    }
}
