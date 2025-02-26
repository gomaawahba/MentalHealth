package com.example.gomaa.Repository;

import com.example.gomaa.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findByTitleContainingIgnoreCase(String title);
}
