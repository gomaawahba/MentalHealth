package com.example.gomaa.Repository;

import com.example.gomaa.entity.ExerciseTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseTrackingRepository extends JpaRepository<ExerciseTracking, Long> {
    List<ExerciseTracking> findByUserId(Long userId);
}
