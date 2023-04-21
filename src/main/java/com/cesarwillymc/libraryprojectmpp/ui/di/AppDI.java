package com.cesarwillymc.libraryprojectmpp.ui.di;

import com.cesarwillymc.libraryprojectmpp.AppController;
import com.cesarwillymc.libraryprojectmpp.data.local.DataAccessFacade;
import com.cesarwillymc.libraryprojectmpp.data.source.login.LoginFactoryDataSource;
import com.cesarwillymc.libraryprojectmpp.usecase.user.IsLoggedUseCase;

public class AppDI {
    public static AppController createAppController() {
        return new AppController(new IsLoggedUseCase(LoginFactoryDataSource.getLoginDataSource(new DataAccessFacade())));
    }
}
