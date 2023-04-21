package com.cesarwillymc.libraryprojectmpp.data.source.account;

import com.cesarwillymc.libraryprojectmpp.domain.entities.LibraryMember;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;

import java.util.List;

public interface MemberDataSource {
    Resource<Void> addNewMember(LibraryMember libraryMember);

    Resource<List<LibraryMember>> getAllMembers();

    Resource<LibraryMember> getMemberById(String id);

    Resource<Void> removeMember(String id);
}
