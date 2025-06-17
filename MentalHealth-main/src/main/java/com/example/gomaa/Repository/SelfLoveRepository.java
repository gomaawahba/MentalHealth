package com.example.gomaa.Repository;

import com.example.gomaa.entity.SelfLove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelfLoveRepository extends JpaRepository<SelfLove, Long> {
    List<SelfLove> findByUserId(Long userId);
}
