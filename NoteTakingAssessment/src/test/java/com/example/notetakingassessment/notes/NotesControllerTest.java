package com.example.notetakingassessment.notes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class NotesControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private NotesRepository notesRepository;

    @InjectMocks
    private NotesController notesController;

    private JacksonTester<List<NotesModel>> jsonNotesModelList;
    private JacksonTester<NotesModel> jsonNotesModel;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(notesController).build();
        this.notesController.notesService = new NotesService(notesRepository);
    }

    @AfterEach
    void tearDown(){
        mvc = null;
        notesRepository = null;
        notesController = null;
        jsonNotesModelList = null;
        jsonNotesModel = null;
    }

    @Test
    void getAllNotes() throws Exception {
        // given
        List<NotesModel> notesList = List.of(
                new NotesModel(1, "note 1"),
                new NotesModel(2, "note 2"));

        given(notesRepository.findAll()).willReturn(notesList);

        // when
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/my-notes/get/all")
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonNotesModelList.write(notesList).getJson());
    }

    @Test
    void getNoteById() throws Exception {
        // given
        NotesModel notes = new NotesModel(1, "note 1");

        given(notesRepository.findById(1L)).willReturn(Optional.of(notes));

        // when
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/my-notes/get/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonNotesModel.write(notes).getJson());
    }

    @Test
    void postNote() throws Exception {
        // when
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/my-notes/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonNotesModel.write(new NotesModel("note 1")).getJson())
        ).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}