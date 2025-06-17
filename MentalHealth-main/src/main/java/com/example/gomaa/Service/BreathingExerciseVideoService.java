package com.example.gomaa.Service;

import com.example.gomaa.dto.BreathingExerciseVideoDto;
import com.example.gomaa.entity.BreathingExerciseVideo;
import com.example.gomaa.Repository.BreathingExerciseVideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BreathingExerciseVideoService {

    private final BreathingExerciseVideoRepository repository;

    public BreathingExerciseVideoService(BreathingExerciseVideoRepository repository) {
        this.repository = repository;
    }

    public List<BreathingExerciseVideoDto> getAllVideos() {
        return repository.findAll().stream()
                .map(video -> new BreathingExerciseVideoDto(
                        video.getId(),
                        video.getName(),
                        video.getImageUrl(),
                        video.getVideoUrl()
                ))
                .collect(Collectors.toList());
    }
}
