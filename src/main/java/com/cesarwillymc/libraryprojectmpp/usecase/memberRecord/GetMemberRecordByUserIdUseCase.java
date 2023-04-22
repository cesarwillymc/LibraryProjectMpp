package com.cesarwillymc.libraryprojectmpp.usecase.memberRecord;

import com.cesarwillymc.libraryprojectmpp.data.source.memberRecord.MemberRecordDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.entities.MemberRecord;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseParams;

import java.util.List;

public class GetMemberRecordByUserIdUseCase extends UseCaseParams<String, Resource<List<MemberRecord>>> {
    final private MemberRecordDataSource memberDataSource;

    public GetMemberRecordByUserIdUseCase(MemberRecordDataSource memberDataSource) {
        this.memberDataSource = memberDataSource;
    }

    @Override
    public Resource<List<MemberRecord>> run(String id) {
        return memberDataSource.getMembersRecordsByUserId(id);
    }
}
