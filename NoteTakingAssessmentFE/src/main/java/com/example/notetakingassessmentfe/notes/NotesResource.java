package com.example.notetakingassessmentfe.notes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/my-notes")
public class NotesResource {

    private static final String POST_NOTE      = "http://localhost:8080/notes/api/v1/my-notes/post";
    private static final String GET_ALL_NOTES  = "http://localhost:8080/notes/api/v1/my-notes/get/all";
    private static final String GET_NOTE_BY_ID = "http://localhost:8080/notes/api/v1/my-notes/get/";

    private static final RestTemplate restTemplate = new RestTemplate();

    public static ResponseEntity<NotesModel> postNote(NotesModel note) {
        return restTemplate.postForEntity(POST_NOTE, note, NotesModel.class);
    }

    public static ResponseEntity<NotesModel> getById(@PathVariable("id") long id) {
        return restTemplate.getForEntity(GET_NOTE_BY_ID + id, NotesModel.class);
    }

    public static ResponseEntity<NotesModel[]> getAll() {
        return restTemplate.getForEntity(GET_ALL_NOTES, NotesModel[].class);
    }
}
