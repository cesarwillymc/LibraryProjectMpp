package com.cesarwillymc.libraryprojectmpp.data.source.author;

import com.cesarwillymc.libraryprojectmpp.domain.entities.LibraryMember;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;

import java.util.List;

public interface AuthorDataSource {

    Resource<Void> addNewAuthor(LibraryMember libraryMember);

    Resource<List<LibraryMember>> getAllAuthor();

    Resource<LibraryMember> getAuthorById(String id);

    Resource<Void> removeAuthor(String id);
}
