package com.example.gomaa.Controller;

import com.example.gomaa.Dto.TimelineDTO;
import com.example.gomaa.Repository.UserRepository;
import com.example.gomaa.Service.TimelineService;
import com.example.gomaa.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timeline")
public class TimelineController {


    private TimelineService timelineService;
    private  UserRepository userRepository;

    @Autowired
    public TimelineController(TimelineService timelineService, UserRepository userRepository) {
        this.timelineService = timelineService;
        this.userRepository = userRepository;
    }

    //this endpoint save data in table timeline for one User
    @PostMapping("/build/{userId}")
    public List<TimelineDTO> buildTimeline(@PathVariable Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("المستخدم غير موجود"));
        return timelineService.buildUserTimeline(userId, user);
    }

    //this Return all data for User
    //
    // جلب البيانات من جدول Timeline بعد بنائها
    @GetMapping("/{userId}")
    public List<TimelineDTO> getUserTimeline(@PathVariable Long userId) {
        return timelineService.getUserTimeline(userId);
    }
}
