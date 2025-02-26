package com.example.gomaa.Controller;

import com.example.gomaa.Dto.VideoDTO;
import com.example.gomaa.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping
    public ResponseEntity<VideoDTO> saveVideo(@RequestBody VideoDTO videoDTO) {
        VideoDTO savedVideo = videoService.saveVideo(videoDTO);
        return ResponseEntity.ok(savedVideo);
    }

    // 🔍 البحث عن فيديو باستخدام العنوان
    @GetMapping("/search")
    public ResponseEntity<VideoDTO> searchVideo(@RequestParam String title) {
        VideoDTO video = videoService.getVideoByTitle(title);
        return ResponseEntity.ok(video);
    }

    @GetMapping
    public ResponseEntity<List<VideoDTO>> getAllVideos() {
        List<VideoDTO> videos = videoService.getAllVideos();
        return ResponseEntity.ok(videos);
    }
}
