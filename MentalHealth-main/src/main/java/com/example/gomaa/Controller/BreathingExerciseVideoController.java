package com.example.gomaa.Controller;

import com.example.gomaa.dto.BreathingExerciseVideoDto;
import com.example.gomaa.Service.BreathingExerciseVideoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breathing-videos")
public class BreathingExerciseVideoController {

    private final BreathingExerciseVideoService service;

    public BreathingExerciseVideoController(BreathingExerciseVideoService service) {
        this.service = service;
    }

    @GetMapping
    public List<BreathingExerciseVideoDto> getAllVideos() {
        return service.getAllVideos();
    }
}
