package com.example.gomaa.Repository;

import com.example.gomaa.entity.SleepContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SleepContentRepository extends JpaRepository<SleepContent, Long> {
    List<SleepContent> findByCategoryId(Long categoryId);
}
