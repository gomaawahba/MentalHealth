package com.example.gomaa.Service;

import com.example.gomaa.Dto.ExerciseTrackingDTO;
import com.example.gomaa.Exception.UserNotFoundException;
import com.example.gomaa.Repository.ExerciseTrackingRepository;
import com.example.gomaa.Repository.UserRepository;
import com.example.gomaa.entity.ExerciseTracking;
import com.example.gomaa.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseTrackingService {

    private ExerciseTrackingRepository exerciseTrackingRepository;

    private UserRepository userRepository;

    @Autowired
    public ExerciseTrackingService(ExerciseTrackingRepository exerciseTrackingRepository, UserRepository userRepository) {
        this.exerciseTrackingRepository = exerciseTrackingRepository;
        this.userRepository = userRepository;
    }

    public ExerciseTrackingDTO saveExercise(ExerciseTrackingDTO dto) {
        Users user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("اسم المستخدم غير موجود"));

        ExerciseTracking exercise = ExerciseTracking.builder()
                .exerciseType(dto.getExerciseType())
                .exerciseDate(dto.getExerciseDate())
                .duration(dto.getDuration())
                .date(LocalDate.now()) // Auto-set current date
                .user(user)
                .build();

        exercise = exerciseTrackingRepository.save(exercise);
        dto.setId(exercise.getId());
        return dto;
    }


    public List<ExerciseTrackingDTO> getExercisesByUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("اسم المستخدم غير موجود"));

        return exerciseTrackingRepository.findByUserId(userId).stream()
                .map(ex -> new ExerciseTrackingDTO(
                        ex.getId(), ex.getExerciseType(), ex.getExerciseDate(),
                        ex.getDuration(), ex.getDate(), ex.getUser().getId()))
                .collect(Collectors.toList());
    }
}
