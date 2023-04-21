package com.cesarwillymc.libraryprojectmpp.data.source.login.entity;

import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.AddressResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.PersonResponse;
import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityData;
import com.cesarwillymc.libraryprojectmpp.domain.entities.TypeAuth;

@EntityData
public class UserResponse extends PersonResponse {
    String id;
    String password;
    TypeAuth authorization;
    String imageUrl;

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