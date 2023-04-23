package com.cesarwillymc.libraryprojectmpp.data.source.customer.entity;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityData;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Author;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
@EntityData
public record BookResponse(
        BookCopyResponse[] copies,
        List<Author> authors,
        String isbn,
        String title,
        LocalDate dateCreated,
        LocalDate dateUpdated) implements Serializable {
    @Override
    public String toString() {
        return "BookResponse{" +
                "copies=" + Arrays.toString(copies) +
                ", authors=" + authors +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                '}';
    }

    @Serial
    private static final long serialVersionUID = 1113799434523451191L;
}
