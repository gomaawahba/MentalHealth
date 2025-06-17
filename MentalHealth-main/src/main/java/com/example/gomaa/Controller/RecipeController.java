package com.example.gomaa.Controller;

import com.example.gomaa.Dto.RecipeDTO;
import com.example.gomaa.Service.RecipeService;
import com.example.gomaa.entity.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/recip")
    public List<Recipe> getAllRecipes2() {
        return recipeService.getAllRecipes2();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }

    //http://localhost:8080/api/recipes/category/مقبلات
    @GetMapping("/category/{category}")
    public ResponseEntity<List<RecipeDTO>> getRecipesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(recipeService.getRecipesByCategory(category));
    }

    @PostMapping
    public ResponseEntity<RecipeDTO> addRecipe(@RequestBody RecipeDTO recipeDTO) {
        return ResponseEntity.ok(recipeService.addRecipe(recipeDTO));
    }
}
