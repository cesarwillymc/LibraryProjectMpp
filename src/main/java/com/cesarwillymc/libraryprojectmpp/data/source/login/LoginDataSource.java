package com.cesarwillymc.libraryprojectmpp.data.source.login;

import com.cesarwillymc.libraryprojectmpp.domain.entities.TypeAuth;
import com.cesarwillymc.libraryprojectmpp.domain.entities.User;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;

public interface LoginDataSource {
    Resource<User> login(String id, String password);

    Resource<Void> logout();

    Resource<TypeAuth> getAuth();

    Resource<Boolean> isLoggedIn();
}
