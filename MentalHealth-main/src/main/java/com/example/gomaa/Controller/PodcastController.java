package com.example.gomaa.Controller;

import com.example.gomaa.Service.PodcastService;
import com.example.gomaa.entity.Podcast;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/self-love/podcasts")
public class PodcastController {
    private final PodcastService service;

    public PodcastController(PodcastService service) {
        this.service = service;
    }

    @GetMapping
    public List<Podcast> getAllPodcasts() {
        return service.getAllPodcasts();
    }

    @PostMapping
    public Podcast createPodcast(@RequestBody Podcast podcast) {
        return service.savePodcast(podcast);
    }

}
