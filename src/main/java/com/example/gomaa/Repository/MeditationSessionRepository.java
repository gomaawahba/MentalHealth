package com.example.gomaa.Repository;

import com.example.gomaa.entity.MeditationSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeditationSessionRepository extends JpaRepository<MeditationSession,Long> {
    List<MeditationSession> findByUserId(Long userId); // Fetch all sessions for a specific user
}
