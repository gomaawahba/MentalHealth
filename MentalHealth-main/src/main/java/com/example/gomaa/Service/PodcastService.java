package com.example.gomaa.Service;

import com.example.gomaa.Repository.PodcastRepository;
import com.example.gomaa.entity.Podcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PodcastService {
    private final PodcastRepository repository;

    @Autowired
    public PodcastService(PodcastRepository repository) {
        this.repository = repository;
    }

    public List<Podcast> getAllPodcasts() {
        return repository.findAll();
    }

    public Podcast savePodcast(Podcast podcast) {
        return repository.save(podcast);
    }
}
