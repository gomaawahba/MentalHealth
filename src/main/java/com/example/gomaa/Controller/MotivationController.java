package com.example.gomaa.Controller;

import com.example.gomaa.Service.MotivationService;
import com.example.gomaa.entity.Motivation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/self-love/motivation")
public class MotivationController {
    private final MotivationService service;

    @Autowired
    public MotivationController(MotivationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Motivation> getAllMotivations() {
        return service.getAllMotivations();
    }

    @PostMapping
    public Motivation createMotivation(@RequestBody Motivation motivation) {
        return service.saveMotivation(motivation);
    }
}
