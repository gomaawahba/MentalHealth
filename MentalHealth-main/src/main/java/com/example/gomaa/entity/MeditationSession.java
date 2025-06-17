package com.example.gomaa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "meditation_sessions")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeditationSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate sessionDate; // The date the user selected

    private int durationMinutes; // Duration in minutes

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Link to User
    private Users user;

}