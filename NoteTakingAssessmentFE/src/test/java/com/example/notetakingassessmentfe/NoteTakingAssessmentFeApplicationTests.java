package com.example.notetakingassessmentfe;

import com.example.notetakingassessmentfe.notes.NotesModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static com.example.notetakingassessmentfe.notes.NotesResource.getAll;
import static com.example.notetakingassessmentfe.notes.NotesResource.postNote;
import static org.assertj.core.api.Assertions.assertThat;

@RestClientTest
class NoteTakingAssessmentFeApplicationTests {

    @Mock
    RestTemplate restTemplate;

    @Test
    void postTwoNotes() {
        // given
        NotesModel note = new NotesModel("note 1");
        NotesModel note2 = new NotesModel("note 2");

        // when
        ResponseEntity<NotesModel> entity = postNote(note);
        ResponseEntity<NotesModel> entity2 = postNote(note2);

        // then
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity2.getStatusCode()).isEqualTo(HttpStatus.OK);

        System.out.println("POST 1 status: " + entity.getStatusCode());
        System.out.println("POST 2 status: " + entity2.getStatusCode());

    }

    @Test
    void getAllNotes() throws JsonProcessingException {
        // when
        ResponseEntity<NotesModel[]> entity = getAll();

        // then
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        System.out.println(Arrays.toString(entity.getBody()));
    }
}
