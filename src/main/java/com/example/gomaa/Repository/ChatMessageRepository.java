package com.example.gomaa.Repository;

import com.example.gomaa.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByUserId(Long userId);

    Optional<ChatMessage> findTopByUserIdOrderByTimestampDesc(Long userId);
}
