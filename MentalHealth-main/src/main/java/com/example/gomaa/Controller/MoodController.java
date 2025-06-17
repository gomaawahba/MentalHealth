package com.example.gomaa.Controller;

import com.example.gomaa.Dto.MoodDTO;
import com.example.gomaa.Dto.MoodHistoryResponse;
import com.example.gomaa.Service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mood")

public class MoodController {
    @Autowired
    private MoodService moodService;

    @PostMapping("/track")
    public ResponseEntity<String> trackMood(@RequestBody MoodDTO moodDTO) {
        String response = moodService.trackMood(moodDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<MoodHistoryResponse>> getUserMoods(@PathVariable Long userId) {

        return ResponseEntity.ok(moodService.getMoodHistory(userId));
    }
}
