package com.cesarwillymc.libraryprojectmpp.ui.login.controller;

import com.cesarwillymc.libraryprojectmpp.domain.entities.User;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.book.entity.SignInParams;
import com.cesarwillymc.libraryprojectmpp.usecase.user.SignInUseCase;

public class LoginControllerImpl implements LoginController {
    private SignInUseCase signInUseCase;

    public LoginControllerImpl(SignInUseCase signInUseCase) {
        this.signInUseCase = signInUseCase;
    }

    @Override
    public Resource<User> signIn(String id, String password) {
        System.out.println("ERROR " + id + "pass " + password);
        return signInUseCase.run(new SignInParams(password, id));
    }

    @Override
    public boolean isValidaSignIn(String id, String password) {
        if (id.isBlank())
            return false;
        return !password.isBlank();
    }
}
