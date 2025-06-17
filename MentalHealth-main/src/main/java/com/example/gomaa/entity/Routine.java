package com.example.gomaa.entity;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "routines")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(nullable = false)
    private String routineType;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoutineDetail> details = new ArrayList<>();

    public Routine() {
    }

    public Routine(Long id, Users user, String routineType, List<RoutineDetail> details) {
        this.id = id;
        this.user = user;
        this.routineType = routineType;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getRoutineType() {
        return routineType;
    }

    public void setRoutineType(String routineType) {
        this.routineType = routineType;
    }

    public List<RoutineDetail> getDetails() {
        return details;
    }

    public void setDetails(List<RoutineDetail> details) {
        this.details = details;
    }
}
