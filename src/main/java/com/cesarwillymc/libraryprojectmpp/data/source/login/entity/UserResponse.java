package com.cesarwillymc.libraryprojectmpp.data.source.login.entity;

import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.AddressResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.PersonResponse;
import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityData;
import com.cesarwillymc.libraryprojectmpp.domain.enums.TypeAuth;

import java.io.Serial;
import java.io.Serializable;

@EntityData
public class UserResponse extends PersonResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1113799434508612345L;

    final String id;
    final String password;
    final TypeAuth authorization;
    final String imageUrl;

    public UserResponse(String f, String l, String t, AddressResponse a, String id, String password, TypeAuth authorization, String imageUrl) {
        super(f, l, t, a);
        this.id = id;
        this.password = password;
        this.authorization = authorization;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public TypeAuth getAuthorization() {
        return authorization;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}