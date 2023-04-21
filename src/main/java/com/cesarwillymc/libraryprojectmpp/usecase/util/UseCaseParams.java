package com.cesarwillymc.libraryprojectmpp.usecase.util;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityDomain;

public abstract class UseCaseParams <I,@EntityDomain O>{
    public abstract O run(I data);
}
