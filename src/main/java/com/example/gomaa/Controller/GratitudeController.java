package com.example.gomaa.Controller;


import com.example.gomaa.Dto.GratitudeDTO;
import com.example.gomaa.Service.GratitudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gratitude")
public class GratitudeController {

    private GratitudeService gratitudeService;

    @Autowired
    public GratitudeController(GratitudeService gratitudeService) {
        this.gratitudeService = gratitudeService;
    }

    //Save Gratitude
    @PostMapping
    public ResponseEntity<GratitudeDTO> saveGratitude(@RequestBody GratitudeDTO dto) {
        return ResponseEntity.ok(gratitudeService.saveGratitude(dto));
    }

    // Get All Gratitude Entries by User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GratitudeDTO>> getAllGratitude(@PathVariable Long userId) {
        return ResponseEntity.ok(gratitudeService.getAllGratitudeByUser(userId));
    }
}
