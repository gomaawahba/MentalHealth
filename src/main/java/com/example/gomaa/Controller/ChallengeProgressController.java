package com.example.gomaa.Controller;

import com.example.gomaa.Dto.ChallengeProgressDTO;
import com.example.gomaa.Service.ChallengeProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenge-progress")
public class ChallengeProgressController {

    private ChallengeProgressService challengeProgressService;

    @Autowired
    public ChallengeProgressController(ChallengeProgressService challengeProgressService) {
        this.challengeProgressService = challengeProgressService;
    }

    @PostMapping("/accept")
    public ResponseEntity<ChallengeProgressDTO> acceptChallenge(@RequestParam Long userId, @RequestParam Long challengeId) {
        return ResponseEntity.ok(challengeProgressService.acceptChallenge(userId, challengeId));
    }

    @PostMapping("/complete/{challengeId}")
    public ResponseEntity<String> completeChallenge(@PathVariable Long challengeId) {
        challengeProgressService.completeChallenge(challengeId);
        return ResponseEntity.ok("تهانينا لقد وجهت تحديًا وتغلبت عليه عملًا جيدًا");
    }

    @GetMapping("/completed/{userId}")
    public ResponseEntity<List<ChallengeProgressDTO>> getUserCompletedChallenges(@PathVariable Long userId) {
        return ResponseEntity.ok(challengeProgressService.getUserCompletedChallenges(userId));
    }


}
