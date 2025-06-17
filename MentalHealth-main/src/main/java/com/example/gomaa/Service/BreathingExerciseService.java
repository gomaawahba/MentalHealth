package com.example.gomaa.Service;

import com.example.gomaa.Dto.BreathingExerciseDTO;
import com.example.gomaa.Exception.UserNotFoundException;
import com.example.gomaa.Repository.BreathingExerciseRepository;
import com.example.gomaa.Repository.UserRepository;
import com.example.gomaa.entity.BreathingExercise;
import com.example.gomaa.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BreathingExerciseService {

    private BreathingExerciseRepository breathingExerciseRepository;
    private UserRepository userRepository;

    @Autowired
    public BreathingExerciseService(BreathingExerciseRepository breathingExerciseRepository, UserRepository userRepository) {
        this.breathingExerciseRepository = breathingExerciseRepository;
        this.userRepository = userRepository;
    }

    public BreathingExerciseDTO saveExercise(BreathingExerciseDTO dto) {
        Users user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("اسم المستخدم غير موجود"));

        BreathingExercise exercise = BreathingExercise.builder()
                .exerciseName(dto.getExerciseName())
                .exerciseDescription(dto.getExerciseDescription())
                .exerciseInstructions(dto.getExerciseInstructions())
                .videoUrl(dto.getVideoUrl())
                .user(user)
                .build();

        exercise = breathingExerciseRepository.save(exercise);
        dto.setId(exercise.getId());
        return dto;
    }

    public List<BreathingExerciseDTO> getExercisesByUserId(Long userId) {
        List<BreathingExercise> exercises = breathingExerciseRepository.findByUserId(userId);
        return exercises.stream()
                .map(ex -> new BreathingExerciseDTO(
                        ex.getId(), ex.getExerciseName(), ex.getExerciseDescription(),
                        ex.getExerciseInstructions(), ex.getVideoUrl(), ex.getUser().getId()))
                .collect(Collectors.toList());
    }
}
