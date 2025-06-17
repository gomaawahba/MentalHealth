package com.example.gomaa.Repository;

import com.example.gomaa.entity.PersonalNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalNoteRepository extends JpaRepository<PersonalNote, Long> {
    List<PersonalNote> findByUserId(Long userId);
}
