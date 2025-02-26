package com.example.gomaa.Controller;

import com.example.gomaa.Service.MusicService;
import com.example.gomaa.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/music")
public class MusicController {

    private final MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    // get All Music
    @GetMapping
    public ResponseEntity<List<Music>> getAllMusic() {
        return ResponseEntity.ok(musicService.getAllMusic());
    }

    //get Music by id
    @GetMapping("/{musicId}")
    public ResponseEntity<Music> getMusicById(@PathVariable Long musicId) {
        return ResponseEntity.ok(musicService.getMusicById(musicId));
    }
}
