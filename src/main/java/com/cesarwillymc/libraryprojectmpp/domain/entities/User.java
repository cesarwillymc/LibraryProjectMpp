package com.cesarwillymc.libraryprojectmpp.domain.entities;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityDomain;
import com.cesarwillymc.libraryprojectmpp.domain.enums.TypeAuth;

@EntityDomain
public class User extends Person{
    String id;
    String password;
    TypeAuth authorization;
    String imageUrl;

    public User(String f, String l, String t, Address a, String id, String password, TypeAuth authorization, String imageUrl) {
        super(f, l, t, a);
        this.id = id;
        this.password = password;
        this.authorization = authorization;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TypeAuth getAuthorization() {
        return authorization;
    }

    public void setAuthorization(TypeAuth authorization) {
        this.authorization = authorization;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", authorization=" + authorization +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}