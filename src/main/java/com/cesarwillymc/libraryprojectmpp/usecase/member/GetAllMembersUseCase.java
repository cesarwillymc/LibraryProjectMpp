package com.cesarwillymc.libraryprojectmpp.usecase.member;

import com.cesarwillymc.libraryprojectmpp.data.source.account.MemberDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.entities.LibraryMember;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseNoParams;

import java.util.List;

public class GetAllMembersUseCase extends UseCaseNoParams<Resource<List<LibraryMember>>> {
    final private MemberDataSource memberDataSource;

    public GetAllMembersUseCase(MemberDataSource memberDataSource) {
        this.memberDataSource = memberDataSource;
    }

    @Override
    public Resource<List<LibraryMember>> run() {
        return memberDataSource.getAllMembers();
    }
}
