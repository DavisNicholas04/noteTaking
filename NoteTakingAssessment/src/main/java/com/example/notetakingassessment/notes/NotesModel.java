package com.example.notetakingassessment.notes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class NotesModel {
    @Id
    @SequenceGenerator(
            name = "notes_sequence",
            sequenceName = "notes_seq",
            allocationSize = 5
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notes_sequence"
    )
    long id;
    String note;

    public NotesModel() {
    }

    public NotesModel(long id, String note) {
        this.id = id;
        this.note = note;
    }

    public NotesModel(String note) {
        this.note = note;
    }


}
