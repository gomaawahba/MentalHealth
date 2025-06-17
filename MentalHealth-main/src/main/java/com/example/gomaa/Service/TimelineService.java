package com.example.gomaa.Service;

import com.example.gomaa.Dto.TimelineDTO;
import com.example.gomaa.Repository.*;
import com.example.gomaa.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimelineService {

    private  TimelineRepository timelineRepository;
    private  SunlightExposureRepository sunlightExposureRepository;
    private  MeditationSessionRepository meditationSessionRepository;
    private  ChallengeProgressRepository challengeProgressRepository;
    private  ExerciseTrackingRepository exerciseTrackingRepository;
    private  BreathingExerciseRepository breathingExerciseRepository;
    private  MoodRepository moodRepository;
    private  GratitudeRepository gratitudeRepository;


    @Autowired
    public TimelineService(TimelineRepository timelineRepository, SunlightExposureRepository sunlightExposureRepository, MeditationSessionRepository meditationSessionRepository, ChallengeProgressRepository challengeProgressRepository, ExerciseTrackingRepository exerciseTrackingRepository, BreathingExerciseRepository breathingExerciseRepository, MoodRepository moodRepository, GratitudeRepository gratitudeRepository) {
        this.timelineRepository = timelineRepository;
        this.sunlightExposureRepository = sunlightExposureRepository;
        this.meditationSessionRepository = meditationSessionRepository;
        this.challengeProgressRepository = challengeProgressRepository;
        this.exerciseTrackingRepository = exerciseTrackingRepository;
        this.breathingExerciseRepository = breathingExerciseRepository;
        this.moodRepository = moodRepository;
        this.gratitudeRepository = gratitudeRepository;
    }

    @Transactional
    public List<TimelineDTO> buildUserTimeline(Long userId, Users user) {
        // حذف سجلات الجدول الزمني القديمة للمستخدم
        timelineRepository.deleteByUserId(userId);

        List<Timeline> timelineEntries = new ArrayList<>();

        // 1. بيانات التعرض للشمس
        List<SunlightExposure> exposures = sunlightExposureRepository.findByUserId(userId);
        for (SunlightExposure exposure : exposures) {
            Timeline t = Timeline.builder()
                    .user(user)
                    .date(exposure.getDate())
                    .description("تعرضت لضوء الشمس لمدة " + exposure.getDuration() + " دقيقة")
                    .build();
            timelineEntries.add(t);
        }

        // 2. بيانات جلسات التأمل
        List<MeditationSession> sessions = meditationSessionRepository.findByUserId(userId);
        for (MeditationSession session : sessions) {
            Timeline t = Timeline.builder()
                    .user(user)
                    .date(session.getSessionDate())
                    .description("جلسة تأمل ("  + ") لمدة " + session.getDurationMinutes() + " دقيقة")
                    .build();
            timelineEntries.add(t);
        }

        // 3. بيانات التحديات
        List<ChallengeProgress> challengeList = challengeProgressRepository.findByUserId(userId);
        for (ChallengeProgress c : challengeList) {
            LocalDate date = c.getCompletionDate();
            String challengeName = (c.getChallenge() != null) ? c.getChallenge().getName() : "تحدي غير معروف";
            String status = c.isCompleted() ? "اكتمل" : "لم يكتمل";
            Timeline t = Timeline.builder()
                    .user(user)
                    .date(date)
                    .description("تحدي: " + challengeName + " - " + status)
                    .build();
            timelineEntries.add(t);
        }

        // 4. بيانات تتبع التمارين (ExerciseTracking)
        List<ExerciseTracking> exerciseTrackings = exerciseTrackingRepository.findByUserId(userId);
        for (ExerciseTracking et : exerciseTrackings) {
            // نفترض أن كيان ExerciseTracking يحتوي على حقل تاريخ (date) وحقل مدة (duration)
            Timeline t = Timeline.builder()
                    .user(user)
                    .date(et.getDate()) // تأكد من وجود طريقة getDate() في ExerciseTracking
                    .description("تم تتبع تمرين رياضي لمدة " + et.getDuration() + " دقيقة")
                    .build();
            timelineEntries.add(t);
        }

        // 5. بيانات تمارين التنفس (BreathingExercise)
        List<BreathingExercise> breathingExercises = breathingExerciseRepository.findByUserId(userId);
        for (BreathingExercise be : breathingExercises) {
            // نفترض أن كيان BreathingExercise يحتوي على حقل تاريخ (date)، اسم التمرين (exerciseName) وحقل مدة (duration)
            Timeline t = Timeline.builder()
                    .user(user)
                    .date(be.getDate()) // تأكد من وجود طريقة getDate() في BreathingExercise
                    .description("تم تنفيذ تمرين التنفس: " + be.getExerciseName())
                    .build();
            timelineEntries.add(t);
        }

        List<Mood> moodEntries = moodRepository.findByUserId(userId);
        for (Mood mood : moodEntries) {
            Timeline t = Timeline.builder()
                    .user(user)
                    .date(mood.getDate())
                    .description("حالتك المزاجية كانت: " + mood.getMoodType())
                    .build();
            timelineEntries.add(t);
        }

        // 🟢 بيانات الامتنان (Gratitude)
        List<Gratitude> gratitudeEntries = gratitudeRepository.findByUserId(userId);
        for (Gratitude gratitude : gratitudeEntries) {
            Timeline t = Timeline.builder()
                    .user(user)
                    .date(gratitude.getCreatedAt().toLocalDate())
                    .description("أنت ممتن لـ: " + gratitude.getText())
                    .build();
            timelineEntries.add(t);
        }

        // حفظ كل السجلات في جدول Timeline
        timelineRepository.saveAll(timelineEntries);

        // فرز القائمة حسب التاريخ (مع التعامل مع القيم null)
        List<Timeline> sortedList = timelineEntries.stream()
                .sorted(Comparator.comparing(
                        t -> t.getDate() != null ? t.getDate() : LocalDate.MIN,
                        Comparator.reverseOrder()
                ))
                .collect(Collectors.toList());

        return sortedList.stream()
                .map(t -> TimelineDTO.builder()
                        .date(t.getDate())
                        .description(t.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    public List<TimelineDTO> getUserTimeline(Long userId) {
        List<Timeline> timeline = timelineRepository.findByUserId(userId);
        return timeline.stream()
                .sorted(Comparator.comparing(
                        t -> t.getDate() != null ? t.getDate() : LocalDate.MIN,
                        Comparator.reverseOrder()
                ))
                .map(t -> TimelineDTO.builder()
                        .date(t.getDate())
                        .description(t.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
