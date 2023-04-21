package com.cesarwillymc.libraryprojectmpp.usecase.user;

import com.cesarwillymc.libraryprojectmpp.data.source.login.LoginDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseNoParams;

public class SignOutUseCase extends UseCaseNoParams< Resource<Void>> {
    final private LoginDataSource loginDataSource;

    public SignOutUseCase(LoginDataSource loginDataSource) {
        this.loginDataSource = loginDataSource;
    }

    public Resource<Void> run() {
        return loginDataSource.logout();
    }
}
