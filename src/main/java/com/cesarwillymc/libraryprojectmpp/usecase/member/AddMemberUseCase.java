package com.cesarwillymc.libraryprojectmpp.usecase.member;

import com.cesarwillymc.libraryprojectmpp.data.source.account.MemberDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.entities.LibraryMember;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseParams;

import java.util.List;

public class AddMemberUseCase extends UseCaseParams<LibraryMember, Resource<Void>> {
    final private MemberDataSource memberDataSource;

    public AddMemberUseCase(MemberDataSource memberDataSource) {
        this.memberDataSource = memberDataSource;
    }

    public Resource<Void> run(LibraryMember libraryMember) {
        return memberDataSource.addNewMember(libraryMember);
    }
}
