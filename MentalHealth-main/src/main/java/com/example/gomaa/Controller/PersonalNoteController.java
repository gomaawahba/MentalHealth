package com.example.gomaa.Controller;

import com.example.gomaa.Dto.PersonalNoteRequestDTO;
import com.example.gomaa.Dto.PersonalNoteResponseDTO;
import com.example.gomaa.Service.PersonalNoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personal-notes")
public class PersonalNoteController {

    private final PersonalNoteService noteService;

    public PersonalNoteController(PersonalNoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<PersonalNoteResponseDTO> addNote(@RequestBody PersonalNoteRequestDTO noteDTO) {
        return ResponseEntity.ok(noteService.addNote(noteDTO));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<PersonalNoteResponseDTO>> getUserNotes(@PathVariable Long userId) {
        return ResponseEntity.ok(noteService.getUserNotes(userId));
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable Long noteId) {
        String message = noteService.deleteNoteById(noteId);
        return ResponseEntity.ok(message);
    }

}
