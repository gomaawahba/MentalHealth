package com.example.gomaa.Controller;

import com.example.gomaa.Dto.SelfLoveDTO;
import com.example.gomaa.Service.SelfLoveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/selflove")
public class SelfLoveController {

    private final SelfLoveService selfLoveService;

    public SelfLoveController(SelfLoveService selfLoveService) {
        this.selfLoveService = selfLoveService;
    }

    @PostMapping
    public SelfLoveDTO saveToFavorites(@RequestParam Long userId, @RequestParam Long categoryId) {
        return selfLoveService.saveToFavorites(userId, categoryId);
    }

    @GetMapping("/{userId}")
    public List<SelfLoveDTO> getUserFavorites(@PathVariable Long userId) {
        return selfLoveService.getFavoritesByUser(userId);

    }}