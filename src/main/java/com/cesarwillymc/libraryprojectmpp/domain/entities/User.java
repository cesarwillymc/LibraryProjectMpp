package com.cesarwillymc.libraryprojectmpp.domain.entities;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityDomain;

@EntityDomain
public record User(
        String id,
        String password,
        TypeAuth authorization
) {
}