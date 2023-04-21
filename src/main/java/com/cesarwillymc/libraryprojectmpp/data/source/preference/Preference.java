package com.cesarwillymc.libraryprojectmpp.data.source.preference;

import com.cesarwillymc.libraryprojectmpp.data.source.login.entity.EmployeeResponse;
import com.cesarwillymc.libraryprojectmpp.domain.entities.TypeAuth;

import java.util.Optional;

public enum Preference {
    TYPE_USER(null);
    private TypeAuth typeAuth;
    private EmployeeResponse user;

    private Preference(EmployeeResponse user) {
        setUser(user);
    }

    public TypeAuth getTypeAuth() {
        return typeAuth;
    }

    public EmployeeResponse getUser() {
        return user;
    }

    public void setUser(EmployeeResponse user) {
        Optional.ofNullable(user).ifPresentOrElse(
                x -> {
                    this.user = user;
                    this.typeAuth = user.authorization();
                },
                () -> this.typeAuth = TypeAuth.NONE
        );
    }

}
