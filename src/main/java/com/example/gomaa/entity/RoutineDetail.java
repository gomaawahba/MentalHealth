package com.example.gomaa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "routine_details")
public class RoutineDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "routine_id", nullable = false)
    private Routine routine;

    @Column(nullable = false)
    private String detail;

    public RoutineDetail() {
    }

    public RoutineDetail(Long id, Routine routine, String detail) {
        this.id = id;
        this.routine = routine;
        this.detail = detail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
