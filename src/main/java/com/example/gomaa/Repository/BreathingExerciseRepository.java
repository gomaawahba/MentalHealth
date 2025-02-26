package com.example.gomaa.Repository;

import com.example.gomaa.entity.BreathingExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreathingExerciseRepository  extends JpaRepository<BreathingExercise, Long> {
    List<BreathingExercise> findByUserId(Long userId);
}

