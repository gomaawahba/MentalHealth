package com.example.gomaa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@SuperBuilder
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    @NotBlank(message = "الاسم مطلوب")
    @Size(min = 3, message = "يجب أن يكون الاسم على الأقل 3 أحرف")
    private String name;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull(message = "تاريخ الميلاد مطلوب")
    private Date birthdate;

    @NotBlank(message = "البريد الإلكتروني مطلوب")
    @Email(message = "البريد الإلكتروني غير صالح")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "البريد الإلكتروني يجب أن يكون Gmail فقط")

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    @NotBlank(message = "الدولة مطلوبة")
    private String country;

    @Column(nullable = false, length = 255)
    @NotBlank(message = "كلمة المرور مطلوبة")
    @Size(min = 8, message = "يجب أن تحتوي كلمة المرور على الأقل 8 أحرف")
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$",
//            message = "يجب أن تحتوي كلمة المرور على الأقل على حرف واحد ورقم واحد")
    private String password;

    private String resetCode; // تخزين كود الاستعادة المؤقت

    // ✅ صور البروفايل والغلاف مع قيم افتراضية
    // تعيين قيم افتراضية للصور
    @Builder.Default
    private String avatar = "https://example.com/default-avatar.png";

    @Builder.Default
    private String coverImage = "https://example.com/default-cover.jpg";

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Mood> moods;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BreathingExercise> exercises;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MeditationSession> meditationSessions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Gratitude> gratitudes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ExerciseTracking> exerciseTrackings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Goal> goals;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SelfLove> selfLoves;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PersonalNote> personalNotes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SunlightExposure>sunlightExposures ;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ChallengeProgress>challengeProgresses ;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Routine>routines ;


/*
* ----------------------------------------------------------------------------
*
* ---------------------------------------------------------------------
*
* */
    public Users() {
    }

    public Users(Long id, String name, Date birthdate, String email, String country, String password) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.country = country;
        this.password = password;
    }

    public Users(Long id, String name, Date birthdate, String email, String country, String password, String resetCode, List<Mood> moods, List<BreathingExercise> exercises, List<MeditationSession> meditationSessions) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.country = country;
        this.password = password;
        this.resetCode = resetCode;
        this.moods = moods;
        this.exercises = exercises;
        this.meditationSessions = meditationSessions;
    }

    public Users(Long userId) {
        this.id = userId;
    }

    public Users(String username, String email, String password) {
        this.email = email;
        this.name = username;
        this.password=password;

    }


    //--------------------------------------------------------------------


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public List<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(List<Routine> routines) {
        this.routines = routines;
    }

    public List<ChallengeProgress> getChallengeProgresses() {
        return challengeProgresses;
    }

    public void setChallengeProgresses(List<ChallengeProgress> challengeProgresses) {
        this.challengeProgresses = challengeProgresses;
    }

    public List<SunlightExposure> getSunlightExposures() {
        return sunlightExposures;
    }

    public void setSunlightExposures(List<SunlightExposure> sunlightExposures) {
        this.sunlightExposures = sunlightExposures;
    }

    public List<SelfLove> getSelfLoves() {
        return selfLoves;
    }

    public void setSelfLoves(List<SelfLove> selfLoves) {
        this.selfLoves = selfLoves;
    }

    public List<PersonalNote> getPersonalNotes() {
        return personalNotes;
    }

    public void setPersonalNotes(List<PersonalNote> personalNotes) {
        this.personalNotes = personalNotes;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public List<ExerciseTracking> getExerciseTrackings() {
        return exerciseTrackings;
    }

    public void setExerciseTrackings(List<ExerciseTracking> exerciseTrackings) {
        this.exerciseTrackings = exerciseTrackings;
    }

    public List<BreathingExercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<BreathingExercise> exercises) {
        this.exercises = exercises;
    }
    public String getResetCode() {
        return resetCode;
    }

    public List<MeditationSession> getMeditationSessions() {
        return meditationSessions;
    }

    public void setMeditationSessions(List<MeditationSession> meditationSessions) {
        this.meditationSessions = meditationSessions;
    }

    public List<Gratitude> getGratitudes() {
        return gratitudes;
    }

    public void setGratitudes(List<Gratitude> gratitudes) {
        this.gratitudes = gratitudes;
    }

    public void setResetCode(String resetCode) {
        this.resetCode = resetCode;
    }
    public List<Mood> getMoods() {
        return moods;
    }

    public void setMoods(List<Mood> moods) {
        this.moods = moods;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
