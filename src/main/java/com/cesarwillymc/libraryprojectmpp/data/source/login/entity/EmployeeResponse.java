package com.cesarwillymc.libraryprojectmpp.data.source.login.entity;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityData;
import com.cesarwillymc.libraryprojectmpp.domain.entities.TypeAuth;

@EntityData
public record EmployeeResponse(
        String id,
        String password,
        TypeAuth authorization
) {
}