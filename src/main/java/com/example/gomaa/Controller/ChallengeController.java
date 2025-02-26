package com.example.gomaa.Controller;

import com.example.gomaa.Dto.ChallengeDTO;
import com.example.gomaa.Service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    private  ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping
    public ResponseEntity<List<ChallengeDTO>> getAllChallenges() {
        return ResponseEntity.ok(challengeService.getAllChallenges());
    }

    @PostMapping
    public ResponseEntity<ChallengeDTO> createChallenge(@RequestBody ChallengeDTO challengeDTO) {
        ChallengeDTO savedChallenge = challengeService.createChallenge(challengeDTO);
        return ResponseEntity.ok(savedChallenge);
    }
}
