package com.cesarwillymc.libraryprojectmpp.ui.di;

import com.cesarwillymc.libraryprojectmpp.data.local.DataAccessFacade;
import com.cesarwillymc.libraryprojectmpp.data.source.login.LoginFactoryDataSource;
import com.cesarwillymc.libraryprojectmpp.ui.login.controller.LoginController;
import com.cesarwillymc.libraryprojectmpp.ui.login.controller.LoginControllerImpl;
import com.cesarwillymc.libraryprojectmpp.usecase.user.SignInUseCase;

public class LoginDI {
    public static LoginController createLoginController() {
        return new LoginControllerImpl(new SignInUseCase(LoginFactoryDataSource.getLoginDataSource(new DataAccessFacade())));
    }
}
