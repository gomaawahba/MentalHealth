package com.example.gomaa.Repository;

import com.example.gomaa.entity.Motivation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivationRepository extends JpaRepository<Motivation, Long> {}