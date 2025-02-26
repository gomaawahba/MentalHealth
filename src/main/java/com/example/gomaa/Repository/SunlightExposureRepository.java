package com.example.gomaa.Repository;

import com.example.gomaa.entity.SunlightExposure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SunlightExposureRepository extends JpaRepository<SunlightExposure, Long> {
    List<SunlightExposure> findByUserId(Long userId);
}