package com.cesarwillymc.libraryprojectmpp.data.source.account.entity;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityData;

/* Immutable */
@EntityData
public record AddressResponse(
        String street,
        String city,
        String state,
        String zip
) {
}
