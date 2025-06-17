package com.example.gomaa.Repository;

import com.example.gomaa.entity.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PodcastRepository extends JpaRepository<Podcast, Long> {}
