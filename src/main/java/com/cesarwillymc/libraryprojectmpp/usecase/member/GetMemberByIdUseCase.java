package com.cesarwillymc.libraryprojectmpp.usecase.member;

import com.cesarwillymc.libraryprojectmpp.data.source.account.MemberDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.entities.LibraryMember;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseNoParams;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseParams;

import java.util.List;

public class GetMemberByIdUseCase extends UseCaseParams<String, Resource<LibraryMember>> {
    final private MemberDataSource memberDataSource;

    public GetMemberByIdUseCase(MemberDataSource memberDataSource) {
        this.memberDataSource = memberDataSource;
    }

    @Override
    public Resource<LibraryMember> run(String id) {
        return memberDataSource.getMemberById(id);
    }
}
