package com.example.gomaa.Repository;


import com.example.gomaa.entity.Gratitude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GratitudeRepository extends JpaRepository<Gratitude, Long> {
    List<Gratitude> findByUserId(Long userId); // Get all gratitude for a user
}
