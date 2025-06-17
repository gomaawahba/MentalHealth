package com.example.gomaa.Repository;


import com.example.gomaa.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal,Long> {

    List<Goal> findByUserId(Long userId);

}
