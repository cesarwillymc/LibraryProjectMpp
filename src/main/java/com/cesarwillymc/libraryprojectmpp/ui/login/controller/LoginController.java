package com.cesarwillymc.libraryprojectmpp.ui.login.controller;

import com.cesarwillymc.libraryprojectmpp.domain.entities.User;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;

public interface LoginController {
    Resource<User> signIn(String id, String password);
    boolean isValidaSignIn(String id, String password);

}
