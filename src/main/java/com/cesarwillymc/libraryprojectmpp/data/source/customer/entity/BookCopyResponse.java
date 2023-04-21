package com.cesarwillymc.libraryprojectmpp.data.source.customer.entity;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityData;

/**
 * Immutable class
 */
@EntityData
public record BookCopyResponse(
        BookResponse book,
        int copyNum,
        boolean isAvailable
) {
}
