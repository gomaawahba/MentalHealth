package com.example.gomaa.Service;

import com.example.gomaa.Dto.PersonalNoteRequestDTO;
import com.example.gomaa.Dto.PersonalNoteResponseDTO;
import com.example.gomaa.Repository.PersonalNoteRepository;
import com.example.gomaa.Repository.UserRepository;
import com.example.gomaa.entity.PersonalNote;
import com.example.gomaa.entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalNoteService {

    private final PersonalNoteRepository noteRepository;
    private final UserRepository userRepository;

    public PersonalNoteService(PersonalNoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public PersonalNoteResponseDTO addNote(PersonalNoteRequestDTO noteDTO) {
        Users user = userRepository.findById(noteDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("اسم المستخدم غير موجود"));

        PersonalNote note = PersonalNote.builder()
                .content(noteDTO.getContent())
                .date(LocalDateTime.now()) // Store the current date
                .user(user)
                .build();

        note = noteRepository.save(note);

        return PersonalNoteResponseDTO.builder()
                .id(note.getId())
                .content(note.getContent())
                .date(note.getDate())
                .build();
    }

    public List<PersonalNoteResponseDTO> getUserNotes(Long userId) {
        return noteRepository.findByUserId(userId)
                .stream()
                .map(note -> PersonalNoteResponseDTO.builder()
                        .id(note.getId())
                        .content(note.getContent())
                        .date(note.getDate())
                        .build()) // No userId in response
                .collect(Collectors.toList());
    }

    //delete
    @Transactional
    public String deleteNoteById(Long noteId) {
        PersonalNote note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("الملاحظة غير موجودة"));
        noteRepository.delete(note);
        return "تم الحذف";
    }

}
