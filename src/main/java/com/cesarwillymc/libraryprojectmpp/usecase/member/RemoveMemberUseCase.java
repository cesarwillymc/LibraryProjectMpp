package com.cesarwillymc.libraryprojectmpp.usecase.member;

import com.cesarwillymc.libraryprojectmpp.data.source.account.MemberDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseParams;

public class RemoveMemberUseCase extends UseCaseParams<String,Resource<Void>> {
    final private MemberDataSource memberDataSource;

    public RemoveMemberUseCase(MemberDataSource memberDataSource) {
        this.memberDataSource = memberDataSource;
    }

    @Override
    public Resource<Void> run(String id) {
        return memberDataSource.removeMember(id);
    }
}
