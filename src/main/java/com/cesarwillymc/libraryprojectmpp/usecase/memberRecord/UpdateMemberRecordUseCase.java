package com.cesarwillymc.libraryprojectmpp.usecase.memberRecord;

import com.cesarwillymc.libraryprojectmpp.data.source.memberRecord.MemberRecordDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.entities.MemberRecord;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.memberRecord.entity.MemberRecordParams;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseParams;

import java.time.LocalDate;

public class UpdateMemberRecordUseCase extends UseCaseParams<MemberRecord, Resource<Void>> {
    final private MemberRecordDataSource memberDataSource;

    public UpdateMemberRecordUseCase(MemberRecordDataSource memberDataSource) {
        this.memberDataSource = memberDataSource;
    }

    @Override
    public Resource<Void> run(MemberRecord data) {
        var currentDate = LocalDate.now();
        data.setDateReturned(currentDate);
        return memberDataSource.updateMemberRecord(data);
    }
}
