package com.cesarwillymc.libraryprojectmpp.ui.profile.controller;

import com.cesarwillymc.libraryprojectmpp.domain.entities.User;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.user.GetUserLoggedUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.user.SignOutUseCase;

public class ProfileController {
    private GetUserLoggedUseCase getUserLoggedUseCase;
    private SignOutUseCase signOutUseCase;

    public ProfileController(GetUserLoggedUseCase getUserLoggedUseCase, SignOutUseCase signOutUseCase) {
        this.getUserLoggedUseCase = getUserLoggedUseCase;
        this.signOutUseCase = signOutUseCase;
    }

    public Resource<User> getInformation() {
        return getUserLoggedUseCase.run();
    }

    public void signOut() {
        signOutUseCase.run();
    }
}
