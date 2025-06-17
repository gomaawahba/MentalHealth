package com.example.gomaa.Service;

import com.example.gomaa.Dto.ChallengeProgressDTO;
import com.example.gomaa.Exception.UserNotFoundException;
import com.example.gomaa.Repository.ChallengeProgressRepository;
import com.example.gomaa.Repository.ChallengeRepository;
import com.example.gomaa.Repository.UserRepository;
import com.example.gomaa.entity.Challenge;
import com.example.gomaa.entity.ChallengeProgress;
import com.example.gomaa.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChallengeProgressService {

    private  ChallengeProgressRepository challengeProgressRepository;
    private UserRepository userRepository;
    private ChallengeRepository challengeRepository;

    @Autowired
    public ChallengeProgressService(ChallengeProgressRepository challengeProgressRepository, UserRepository userRepository, ChallengeRepository challengeRepository) {
        this.challengeProgressRepository = challengeProgressRepository;
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
    }
    public ChallengeProgressDTO acceptChallenge(Long userId, Long challengeId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("المستخدم غير موجود"));
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new RuntimeException("التحدي غير موجود"));

        ChallengeProgress progress = ChallengeProgress.builder()
                .user(user)
                .challenge(challenge)
                .completed(false)
                .build();

        challengeProgressRepository.save(progress);

        return ChallengeProgressDTO.builder()
                .id(progress.getId())
                .userId(userId)
                .challengeId(challengeId)
                .completed(false)
                .build();
    }

    public ChallengeProgressDTO completeChallenge(Long progressId) {
        ChallengeProgress progress = challengeProgressRepository.findById(progressId)
                .orElseThrow(() -> new RuntimeException("التحدي غير موجود"));

        progress.setCompleted(true);
        progress.setCompletionDate(LocalDate.now());
        challengeProgressRepository.save(progress);

        return ChallengeProgressDTO.builder()
                .id(progress.getId())
                .userId(progress.getUser().getId())
                .challengeId(progress.getChallenge().getId())
                .completed(true)
                .completionDate(progress.getCompletionDate())
                .build();
    }

    public List<ChallengeProgressDTO> getUserCompletedChallenges(Long userId) {
        return challengeProgressRepository.findByUserId(userId).stream()
                .filter(ChallengeProgress::isCompleted)
                .map(progress -> ChallengeProgressDTO.builder()
                        .id(progress.getId())
                        .userId(progress.getUser().getId())
                        .challengeId(progress.getChallenge().getId())
                        .completed(true)
                        .completionDate(progress.getCompletionDate())
                        .build()).collect(Collectors.toList());
    }


}
