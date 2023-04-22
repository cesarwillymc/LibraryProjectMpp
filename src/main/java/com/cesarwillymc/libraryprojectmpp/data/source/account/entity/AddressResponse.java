package com.cesarwillymc.libraryprojectmpp.data.source.account.entity;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityData;

import java.io.Serial;
import java.io.Serializable;

/* Immutable */
@EntityData
public record AddressResponse(
        String street,
        String city,
        String state,
        String zip
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1113799434511123213L;
}
