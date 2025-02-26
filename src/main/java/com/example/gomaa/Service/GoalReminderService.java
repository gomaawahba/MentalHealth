package com.example.gomaa.Service;

import com.example.gomaa.Repository.GoalRepository;
import com.example.gomaa.entity.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class GoalReminderService {

    private final GoalRepository goalRepository;
    private final NotificationService notificationService;

    @Autowired
    public GoalReminderService(GoalRepository goalRepository, NotificationService notificationService) {
        this.goalRepository = goalRepository;
        this.notificationService = notificationService;
    }

    @Scheduled(cron = "0 * * * * *") // Runs every minute
    public void sendGoalReminders() {
        List<Goal> todayGoals = goalRepository.findAll().stream()
                .filter(goal -> goal.getTargetDate().isEqual(LocalDate.now()) &&
                        goal.getReminderTime().equals(LocalTime.now().withSecond(0).withNano(0)))
                .toList();

        for (Goal goal : todayGoals) {
            String message = " تذكير: لديك هدف يجب تحقيقه اليوم! " + goal.getGoalTitle();
            notificationService.sendNotification(goal.getUser().getId(), message);
        }
    }

    @Scheduled(cron = "0 0 0 * * *") // Runs at midnight
    public void checkUnfinishedGoals() {
        List<Goal> expiredGoals = goalRepository.findAll().stream()
                .filter(goal -> goal.getTargetDate().isBefore(LocalDate.now()) && !goal.isCompleted())
                .toList();

        for (Goal goal : expiredGoals) {
            String message = "⚠️ لم تكمل هدفك: " + goal.getGoalTitle();
            notificationService.sendNotification(goal.getUser().getId(), message);
        }
    }
}
