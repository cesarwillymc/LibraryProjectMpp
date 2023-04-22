package com.cesarwillymc.libraryprojectmpp.usecase.memberRecord;

import com.cesarwillymc.libraryprojectmpp.data.source.memberRecord.MemberRecordDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.entities.MemberRecord;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseNoParams;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseParams;

import java.util.List;

public class GetAllMembersRecordUseCase extends UseCaseNoParams<Resource<List<MemberRecord>>> {
    final private MemberRecordDataSource memberDataSource;

    public GetAllMembersRecordUseCase(MemberRecordDataSource memberDataSource) {
        this.memberDataSource = memberDataSource;
    }

    @Override
    public Resource<List<MemberRecord>> run() {
        return memberDataSource.getAllMemberRecord();
    }
}
