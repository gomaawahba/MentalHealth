package com.example.gomaa.Service;

import com.example.gomaa.Dto.MoodDTO;
import com.example.gomaa.Dto.MoodHistoryResponse;
import com.example.gomaa.Exception.UserNotFoundException;
import com.example.gomaa.Repository.MoodRepository;
import com.example.gomaa.Repository.UserRepository;
import com.example.gomaa.entity.Mood;
import com.example.gomaa.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoodService {

    private MoodRepository moodRepository;

    private UserRepository userRepository;

    @Autowired
    public MoodService(MoodRepository moodRepository, UserRepository userRepository) {
        this.moodRepository = moodRepository;
        this.userRepository = userRepository;
    }

    public String trackMood(MoodDTO moodDTO) {
        Users user = userRepository.findById(moodDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("المستخدم غير موجود"));

        Mood mood = new Mood();
        mood.setMoodType(moodDTO.getMoodType());
        mood.setDate(LocalDate.now());
        mood.setUser(user);

        moodRepository.save(mood);
        return "تم تسجيل المزاج بنجاح!";
    }

    public List<MoodHistoryResponse> getMoodHistory(Long userId) {
        List<Mood> moods = moodRepository.findByUserId(userId);
        return moods.stream()
                .map(MoodHistoryResponse::new)
                .collect(Collectors.toList());
    }
}
