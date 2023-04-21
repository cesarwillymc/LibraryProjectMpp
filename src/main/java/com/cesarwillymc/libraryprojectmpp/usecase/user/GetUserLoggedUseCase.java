package com.cesarwillymc.libraryprojectmpp.usecase.user;

import com.cesarwillymc.libraryprojectmpp.data.source.login.LoginDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.entities.User;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseNoParams;

public class GetUserLoggedUseCase extends UseCaseNoParams<Resource<User>> {
    final private LoginDataSource loginDataSource;

    public GetUserLoggedUseCase(LoginDataSource loginDataSource) {
        this.loginDataSource = loginDataSource;
    }

    public Resource<User> run() {
        return loginDataSource.getAuth();
    }
}
