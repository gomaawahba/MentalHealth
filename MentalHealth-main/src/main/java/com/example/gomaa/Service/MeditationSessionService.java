package com.example.gomaa.Service;

import com.example.gomaa.Dto.MeditationSessionDTO;
import com.example.gomaa.Exception.UserNotFoundException;
import com.example.gomaa.Repository.MeditationSessionRepository;
import com.example.gomaa.Repository.UserRepository;
import com.example.gomaa.entity.MeditationSession;
import com.example.gomaa.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeditationSessionService {

    private MeditationSessionRepository meditationSessionRepository;

    private UserRepository userRepository;

    @Autowired
    public MeditationSessionService(MeditationSessionRepository meditationSessionRepository, UserRepository userRepository) {
        this.meditationSessionRepository = meditationSessionRepository;
        this.userRepository = userRepository;
    }

    // Fetch all meditation sessions by user
    public List<MeditationSessionDTO> getSessionsByUser(Long userId) {
        List<MeditationSession> sessions = meditationSessionRepository.findByUserId(userId);
        return sessions.stream()
                .map(session -> new MeditationSessionDTO(
                        session.getId()
                       , session.getSessionDate(),
                        session.getDurationMinutes(), session.getUser().getId()))
                .collect(Collectors.toList());
    }

    public MeditationSessionDTO saveSession(MeditationSessionDTO dto) {
        // Validate if the user exists
        Users user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("اسم المستخدم غير موجود"));

        // Build the Meditation Session object
        MeditationSession session = MeditationSession.builder()
                .sessionDate(dto.getSessionDate())
                .durationMinutes(dto.getDurationMinutes())
                .user(user) // Set validated User
                .build();

        // Save the session
        session = meditationSessionRepository.save(session);

        // Return the saved session as DTO
        return new MeditationSessionDTO(
                session.getId(),
                session.getSessionDate(),
                session.getDurationMinutes(),
                session.getUser().getId()
        );
    }



}
