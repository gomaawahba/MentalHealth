package com.example.gomaa.Controller;

import com.example.gomaa.Dto.MeditationSessionDTO;
import com.example.gomaa.Service.MeditationSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meditation")
public class MeditationSessionController {
    private MeditationSessionService meditationSessionService;

    @Autowired
    public MeditationSessionController(MeditationSessionService meditationSessionService) {
        this.meditationSessionService = meditationSessionService;
    }

    // Get all meditation sessions for a user
    @GetMapping("/user/{id}")
    public List<MeditationSessionDTO> getSessionsByUser(@PathVariable Long id) {
        return meditationSessionService.getSessionsByUser(id);
    }

    // Save a new meditation session
    @PostMapping
    public ResponseEntity<String> saveSession(@RequestBody MeditationSessionDTO dto) {
        MeditationSessionDTO savedSession = meditationSessionService.saveSession(dto);
        String responseMessage = "تم حفظ تاريخ تأملك: " + savedSession.getSessionDate() +
                " ومدة التأمل: " + savedSession.getDurationMinutes() + " دقيقة";
        return ResponseEntity.ok(responseMessage);
    }

}
