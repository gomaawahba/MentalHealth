package com.example.gomaa.Service;

import com.example.gomaa.Dto.CategorySelfLoveDTO;
import com.example.gomaa.Repository.CategorySelfLoveRepository;
import com.example.gomaa.entity.CategorySelfLove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategorySelfLoveService {

    private CategorySelfLoveRepository categorySelfLoveRepository;

    @Autowired
    public CategorySelfLoveService(CategorySelfLoveRepository categorySelfLoveRepository) {
        this.categorySelfLoveRepository = categorySelfLoveRepository;
    }

    public CategorySelfLoveDTO addCategory(CategorySelfLoveDTO dto) {
        CategorySelfLove category = CategorySelfLove.builder()
                .type(dto.getType())
                .content(dto.getContent())
                .build();

        category = categorySelfLoveRepository.save(category);

        return convertToDTO(category);
    }

    public List<CategorySelfLoveDTO> getAllCategories() {
        return categorySelfLoveRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CategorySelfLoveDTO convertToDTO(CategorySelfLove category) {
        return CategorySelfLoveDTO.builder()
                .id(category.getId())
                .type(category.getType())
                .content(category.getContent())
                .build();
    }
}
