package com.example.gomaa.Repository;

import com.example.gomaa.entity.SleepCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SleepCategoryRepository extends JpaRepository<SleepCategory, Long> {
    Optional<SleepCategory> findByName(String name);
}
