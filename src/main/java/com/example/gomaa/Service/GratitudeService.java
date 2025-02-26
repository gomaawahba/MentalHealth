package com.example.gomaa.Service;

import com.example.gomaa.Dto.GratitudeDTO;
import com.example.gomaa.Exception.UserNotFoundException;
import com.example.gomaa.Repository.GratitudeRepository;
import com.example.gomaa.Repository.UserRepository;
import com.example.gomaa.entity.Gratitude;
import com.example.gomaa.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GratitudeService {

    private GratitudeRepository gratitudeRepository;

    private UserRepository userRepository;

    @Autowired
    public GratitudeService(GratitudeRepository gratitudeRepository, UserRepository userRepository) {
        this.gratitudeRepository = gratitudeRepository;
        this.userRepository = userRepository;
    }

    // Save Gratitude Entry
    public GratitudeDTO saveGratitude(GratitudeDTO dto) {
        // Validate if user exists
        Users user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("اسم المستخدم غير موجود"));

        // Create gratitude entry
        Gratitude gratitude = Gratitude.builder()
                .text(dto.getText())
                .createdAt(LocalDateTime.now()) // Set timestamp
                .user(user)
                .build();

        gratitude = gratitudeRepository.save(gratitude);

        // Convert to DTO
        return new GratitudeDTO(gratitude.getId(), gratitude.getText(), gratitude.getCreatedAt(), user.getId());
    }

    public List<GratitudeDTO> getAllGratitudeByUser(Long userId) {

        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("اسم المستخدم غير موجود"));


        return gratitudeRepository.findByUserId(userId).stream()
                .map(gr -> new GratitudeDTO(gr.getId(), gr.getText(), gr.getCreatedAt(), gr.getUser().getId()))
                .collect(Collectors.toList());
    }

}
