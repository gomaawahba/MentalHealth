package com.example.gomaa.Service;

import com.example.gomaa.Dto.VideoDTO;
import com.example.gomaa.Repository.VideoRepository;
import com.example.gomaa.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }


    public VideoDTO saveVideo(VideoDTO videoDTO) {
        Video video = new Video();
        video.setTitle(videoDTO.getTitle());
        video.setThumbnailUrl(videoDTO.getThumbnailUrl());
        video.setVideoUrl(videoDTO.getVideoUrl());

        Video savedVideo = videoRepository.save(video);

        return new VideoDTO(savedVideo.getId(), savedVideo.getTitle(), savedVideo.getThumbnailUrl(), savedVideo.getVideoUrl());
    }

    // 🔍 البحث عن فيديو باستخدام العنوان
    public VideoDTO getVideoByTitle(String title) {
        return videoRepository.findByTitleContainingIgnoreCase(title)
                .map(v -> new VideoDTO(v.getId(), v.getTitle(), v.getThumbnailUrl(), v.getVideoUrl()))
                .orElseThrow(() -> new RuntimeException("الفيديو غير موجود"));
    }

    public List<VideoDTO> getAllVideos() {
        List<Video> videos = videoRepository.findAll();
        return videos.stream()
                .map(v -> new VideoDTO(v.getId(), v.getTitle(), v.getThumbnailUrl(), v.getVideoUrl()))
                .collect(Collectors.toList());
    }
}
