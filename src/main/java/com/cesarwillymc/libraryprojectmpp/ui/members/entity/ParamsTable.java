package com.cesarwillymc.libraryprojectmpp.ui.members.entity;

import com.cesarwillymc.libraryprojectmpp.domain.enums.TypeStatusReturnBook;

public class ParamsTable{
    final TypeStatusReturnBook typeStatusReturnBook;
    final String idCheckOut;

    public ParamsTable(TypeStatusReturnBook typeStatusReturnBook, String idCheckOut) {
        this.typeStatusReturnBook = typeStatusReturnBook;
        this.idCheckOut = idCheckOut;
    }

    public TypeStatusReturnBook getTypeStatusReturnBook() {
        return typeStatusReturnBook;
    }

    public String getIdCheckOut() {
        return idCheckOut;
    }
}