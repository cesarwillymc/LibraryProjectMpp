package com.cesarwillymc.libraryprojectmpp;

import com.cesarwillymc.libraryprojectmpp.usecase.user.IsLoggedUseCase;

public class AppController {
    private IsLoggedUseCase isLoggedUseCase;
    public AppController(IsLoggedUseCase isLoggedUseCase){
        this.isLoggedUseCase =isLoggedUseCase;
    }

    boolean isLogged(){
        return isLoggedUseCase.run().getData();
    }
}
