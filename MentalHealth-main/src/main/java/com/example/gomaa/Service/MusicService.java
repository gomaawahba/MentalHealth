package com.example.gomaa.Service;

import com.example.gomaa.Repository.MusicRepository;
import com.example.gomaa.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {

    private final MusicRepository musicRepository;

    @Autowired
    public MusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public List<Music> getAllMusic() {
        return musicRepository.findAll();
    }

    public Music getMusicById(Long id) {
        return musicRepository.findById(id).orElseThrow(() -> new RuntimeException("الموسيقى غير موجودة"));
    }
}
