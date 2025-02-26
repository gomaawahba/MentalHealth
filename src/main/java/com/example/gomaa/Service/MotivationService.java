package com.example.gomaa.Service;

import com.example.gomaa.Repository.MotivationRepository;
import com.example.gomaa.entity.Motivation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotivationService {
    private final MotivationRepository repository;

    @Autowired
    public MotivationService(MotivationRepository repository) {
        this.repository = repository;
    }

    public List<Motivation> getAllMotivations() {
        return repository.findAll();
    }

    public Motivation saveMotivation(Motivation motivation) {
        return repository.save(motivation);
    }
}
