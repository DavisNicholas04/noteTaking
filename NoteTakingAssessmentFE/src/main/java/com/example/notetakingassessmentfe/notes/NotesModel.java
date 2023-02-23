package com.example.notetakingassessmentfe.notes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotesModel {

    long id;
    String note;

    public NotesModel() {

    }

    public NotesModel(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", note='" + note + '\'' +
                '}';
    }
}
