package com.cesarwillymc.libraryprojectmpp.data.source.customer.entity;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityData;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Author;

import java.time.LocalDate;
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
        Integer maxCheckoutLength,
        LocalDate dateCreated,
        LocalDate dateUpdated) {
}
