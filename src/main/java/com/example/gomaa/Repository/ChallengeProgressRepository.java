package com.example.gomaa.Repository;

import com.example.gomaa.entity.ChallengeProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeProgressRepository extends JpaRepository<ChallengeProgress, Long> {
    List<ChallengeProgress> findByUserId(Long userId);
}
