package com.cesarwillymc.libraryprojectmpp.data.source.preference;

import com.cesarwillymc.libraryprojectmpp.data.source.login.entity.UserResponse;
import com.cesarwillymc.libraryprojectmpp.domain.entities.TypeAuth;

import java.util.Optional;

public enum Preference {
    TYPE_USER(null);
    private TypeAuth typeAuth;
    private UserResponse user;

    private Preference(UserResponse user) {
        setUser(user);
    }

    public TypeAuth getTypeAuth() {
        return typeAuth;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        Optional.ofNullable(user).ifPresentOrElse(
                x -> {
                    this.user = user;
                    this.typeAuth = user.getAuthorization();
                },
                () -> this.typeAuth = TypeAuth.NONE
        );
    }

}
