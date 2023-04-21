package com.cesarwillymc.libraryprojectmpp.usecase.memberRecord;

import com.cesarwillymc.libraryprojectmpp.data.source.memberRecord.MemberRecordDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.entities.MemberRecord;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseParams;

import java.util.List;

public class GetTotalBooksBorrowUseCase extends UseCaseParams<String, Resource<List<MemberRecord>>> {
    final private MemberRecordDataSource memberDataSource;

    public GetTotalBooksBorrowUseCase(MemberRecordDataSource memberDataSource) {
        this.memberDataSource = memberDataSource;
    }

    @Override
    public Resource<List<MemberRecord>> run(String id) {
        var resource = memberDataSource.getAllMemberRecord();
        if (resource.isSuccess()) {
            return Resource.Sucess(memberDataSource.getAllMemberRecord().getData().stream().filter(x -> !x.getWasReturned()).toList());
        } else {
            return resource;
        }
    }
}
