package com.example.notetakingassessment.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "api/v1/my-notes")
@RestController
public class NotesController {

    NotesService notesService;

    @Autowired
    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<List<NotesModel>> getAllNotes() {
        return notesService.getAllNotes();
    }

    @GetMapping(path = {"/get/{id}"})
    public ResponseEntity<NotesModel> getNoteById(@PathVariable("id") long id) {
        return notesService.getNoteById(id);
    }

    @PostMapping(path = "/post")
    public ResponseEntity<NotesModel> postNote(@RequestBody NotesModel note) {
        return notesService.saveNote(note);
    }
}