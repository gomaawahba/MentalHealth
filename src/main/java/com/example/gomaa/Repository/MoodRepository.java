package com.example.gomaa.Repository;

import com.example.gomaa.entity.Mood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MoodRepository extends JpaRepository<Mood,Long> {
    // Custom query methods can be added here

    // Example: Find moods by user's ID
    List<Mood> findByUserId(Long userId);


}
