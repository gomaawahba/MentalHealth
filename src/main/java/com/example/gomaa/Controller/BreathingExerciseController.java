package com.example.gomaa.Controller;

import com.example.gomaa.Dto.BreathingExerciseDTO;
import com.example.gomaa.Service.BreathingExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breathing")
public class BreathingExerciseController {
    private BreathingExerciseService breathingExerciseService;

    public BreathingExerciseController(BreathingExerciseService breathingExerciseService) {
        this.breathingExerciseService = breathingExerciseService;
    }

    @PostMapping("/save")
    public ResponseEntity<BreathingExerciseDTO> saveExercise(@RequestBody BreathingExerciseDTO dto) {
        return ResponseEntity.ok(breathingExerciseService.saveExercise(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BreathingExerciseDTO>> getUserExercises(@PathVariable Long userId) {
        return ResponseEntity.ok(breathingExerciseService.getExercisesByUserId(userId));
    }
}
