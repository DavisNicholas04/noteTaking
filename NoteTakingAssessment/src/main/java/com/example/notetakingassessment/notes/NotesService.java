package com.example.notetakingassessment.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotesService {
    NotesRepository notesRepository;

    @Autowired
    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public ResponseEntity<List<NotesModel>> getAllNotes() {
        try {
            List<NotesModel> allNotes = notesRepository.findAll();
            return new ResponseEntity<>(allNotes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<NotesModel> getNoteById(long id) {
        Optional<NotesModel> note = notesRepository.findById(id);
        if (note.isPresent())
            return new ResponseEntity<>(note.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<NotesModel> saveNote(NotesModel note) {
        if (note.note == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            return new ResponseEntity<>(notesRepository.save(note), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}