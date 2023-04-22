package com.cesarwillymc.libraryprojectmpp.data.source.customer.entity;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityData;

import java.io.Serializable;

/**
 * Immutable class
 */
@EntityData
public record BookCopyResponse(
        String isbn,
        int copyNum,
        boolean isAvailable
) implements Serializable {
    private static final long serialVersionUID = 1113799434518616191L;
}
