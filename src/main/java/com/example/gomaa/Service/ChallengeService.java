package com.example.gomaa.Service;

import com.example.gomaa.Dto.ChallengeDTO;
import com.example.gomaa.Repository.ChallengeRepository;
import com.example.gomaa.entity.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChallengeService {

    private ChallengeRepository challengeRepository;

    @Autowired
    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public List<ChallengeDTO> getAllChallenges() {
        return challengeRepository.findAll().stream().map(ch -> ChallengeDTO.builder()
                .id(ch.getId())
                .name(ch.getName())
                .category(ch.getCategory())
                .build()).collect(Collectors.toList());
    }

    public ChallengeDTO createChallenge(ChallengeDTO challengeDTO) {
        Challenge challenge = Challenge.builder()
                .name(challengeDTO.getName())

                .category(challengeDTO.getCategory())
                .build();

        Challenge savedChallenge = challengeRepository.save(challenge);

        return ChallengeDTO.builder()
                .id(savedChallenge.getId())
                .name(savedChallenge.getName())
                .category(savedChallenge.getCategory())
                .build();
    }

}
