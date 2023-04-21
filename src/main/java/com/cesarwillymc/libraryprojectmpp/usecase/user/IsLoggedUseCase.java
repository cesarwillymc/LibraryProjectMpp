package com.cesarwillymc.libraryprojectmpp.usecase.user;

import com.cesarwillymc.libraryprojectmpp.data.source.login.LoginDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseNoParams;

public class IsLoggedUseCase extends UseCaseNoParams< Resource<Boolean>> {
    final private LoginDataSource loginDataSource;

    public IsLoggedUseCase(LoginDataSource loginDataSource) {
        this.loginDataSource = loginDataSource;
    }

    public Resource<Boolean> run() {
        return loginDataSource.isLoggedIn();
    }
}
