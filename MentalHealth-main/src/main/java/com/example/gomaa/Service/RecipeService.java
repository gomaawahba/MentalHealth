package com.example.gomaa.Service;

import com.example.gomaa.Dto.RecipeDTO;
import com.example.gomaa.Repository.RecipeRepository;
import com.example.gomaa.entity.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeDTO> getAllRecipes() {
        return recipeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    //get all Recip
    public List<Recipe> getAllRecipes2() {
        return recipeRepository.findAll();
    }



    public List<RecipeDTO> getRecipesByCategory(String category) {
        return recipeRepository.findByCategory(category).stream()
                .map(recipe -> new RecipeDTO(recipe.getName(), recipe.getCategory(),
                        recipe.getImageUrl(), recipe.getPreparation()))
                .collect(Collectors.toList());
    }

    public RecipeDTO getRecipeById(Long id) {
        return recipeRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public RecipeDTO addRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = convertToEntity(recipeDTO);
        return convertToDTO(recipeRepository.save(recipe));
    }

    private RecipeDTO convertToDTO(Recipe recipe) {
        RecipeDTO dto = new RecipeDTO();
        dto.setName(recipe.getName());
        dto.setCategory(recipe.getCategory());
        dto.setImageUrl(recipe.getImageUrl());
        dto.setPreparation(recipe.getPreparation());
        return dto;
    }

    private Recipe convertToEntity(RecipeDTO dto) {
        Recipe recipe = new Recipe();
        recipe.setName(dto.getName());
        recipe.setCategory(dto.getCategory());
        recipe.setImageUrl(dto.getImageUrl());
        recipe.setPreparation(dto.getPreparation());
        return recipe;
    }
}
