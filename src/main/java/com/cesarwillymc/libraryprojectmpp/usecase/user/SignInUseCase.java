package com.cesarwillymc.libraryprojectmpp.usecase.user;

import com.cesarwillymc.libraryprojectmpp.data.source.login.LoginDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.entities.User;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.book.entity.SignInParams;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseParams;

public class SignInUseCase extends UseCaseParams<SignInParams, Resource<User>> {
    final private LoginDataSource loginDataSource;

    public SignInUseCase(LoginDataSource loginDataSource) {
        this.loginDataSource = loginDataSource;
    }

    public Resource<User> run(SignInParams sign) {
        return loginDataSource.login(sign.user(),sign.password());
    }
}
