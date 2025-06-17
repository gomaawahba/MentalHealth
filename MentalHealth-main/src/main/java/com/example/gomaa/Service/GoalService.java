package com.example.gomaa.Service;


import com.example.gomaa.Dto.GoalDTO;
import com.example.gomaa.Exception.GoalNotFoundException;
import com.example.gomaa.Exception.UserNotFoundException;
import com.example.gomaa.Repository.GoalRepository;
import com.example.gomaa.Repository.UserRepository;
import com.example.gomaa.entity.Goal;
import com.example.gomaa.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalService {
    private GoalRepository goalRepository;

    private UserRepository userRepository;

    @Autowired
    public GoalService(GoalRepository goalRepository, UserRepository userRepository) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
    }

    //save goal
    public GoalDTO saveGoal(GoalDTO dto) {
        Users user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("اسم المستخدم غير موجود"));

        Goal goal = Goal.builder()
                .goalTitle(dto.getGoalTitle())
                .createdDate(LocalDate.now()) // Set current date
                .reminderTime(dto.getReminderTime())
                .targetDate(dto.getTargetDate())
                .isCompleted(false) // Default is false
                .user(user)
                .build();

        goal = goalRepository.save(goal);
        dto.setId(goal.getId());
        return dto;
    }

    //get All Goals
    public List<GoalDTO> getGoalsByUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("اسم المستخدم غير موجود"));

        return goalRepository.findByUserId(userId).stream()
                .map(goal -> new GoalDTO(
                        goal.getId(), goal.getGoalTitle(), goal.getCreatedDate(),
                        goal.getReminderTime(), goal.getTargetDate(),
                        goal.isCompleted(), goal.getUser().getId()))
                .collect(Collectors.toList());
    }

    //goal completed
    public void markGoalCompleted(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new GoalNotFoundException("الهدف غير موجود"));

        goal.setCompleted(true);
        goalRepository.save(goal);
    }

    //goal Not Complete
    public void markGoalNotCompleted(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new GoalNotFoundException("الهدف غير موجود"));

        goal.setCompleted(false);
        goalRepository.save(goal);
    }
}
