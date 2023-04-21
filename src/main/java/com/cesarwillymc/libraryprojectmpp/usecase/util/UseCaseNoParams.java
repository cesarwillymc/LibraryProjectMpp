package com.cesarwillymc.libraryprojectmpp.usecase.util;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityDomain;

public abstract class UseCaseNoParams<@EntityDomain O>{
    public abstract O run();
}
