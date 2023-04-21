package com.cesarwillymc.libraryprojectmpp.data.source.author;

import com.cesarwillymc.libraryprojectmpp.domain.entities.LibraryMember;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;

import java.util.List;

public class AuthorRepository implements   AuthorDataSource{

    @Override
    public Resource<Void> addNewAuthor(LibraryMember libraryMember) {
        return null;
    }

    @Override
    public Resource<List<LibraryMember>> getAllAuthor() {
        return null;
    }

    @Override
    public Resource<LibraryMember> getAuthorById(String id) {
        return null;
    }

    @Override
    public Resource<Void> removeAuthor(String id) {
        return null;
    }
}
