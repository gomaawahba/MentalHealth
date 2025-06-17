package com.example.gomaa.Controller;

import com.example.gomaa.Dto.ExerciseTrackingDTO;
import com.example.gomaa.Service.ExerciseTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseTrackingController {

    private ExerciseTrackingService exerciseTrackingServicel;

    @Autowired
    public ExerciseTrackingController(ExerciseTrackingService exerciseTrackingServicel) {
        this.exerciseTrackingServicel = exerciseTrackingServicel;
    }

    @PostMapping("/save")
    public ExerciseTrackingDTO saveExercise(@RequestBody ExerciseTrackingDTO dto) {
        return exerciseTrackingServicel.saveExercise(dto);
    }

    // ✅ Get all exercises for a user
    @GetMapping("/user/{userId}")
    public List<ExerciseTrackingDTO> getExercisesByUser(@PathVariable Long userId) {
        return exerciseTrackingServicel.getExercisesByUser(userId);
    }
}
