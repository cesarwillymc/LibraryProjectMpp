package com.cesarwillymc.libraryprojectmpp.usecase.memberRecord;

import com.cesarwillymc.libraryprojectmpp.data.source.memberRecord.MemberRecordDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.entities.MemberRecord;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.memberRecord.entity.MemberRecordParams;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseParams;

import java.time.LocalDate;

public class AddMemberRecordUseCase extends UseCaseParams<MemberRecordParams, Resource<Void>> {
    final private MemberRecordDataSource memberDataSource;
    private final static String PATH = "book";
    private final static Integer MONTH_DUE = 1;

    public AddMemberRecordUseCase(MemberRecordDataSource memberDataSource) {
        this.memberDataSource = memberDataSource;
    }

    @Override
    public Resource<Void> run(MemberRecordParams data) {
        var currentDate = LocalDate.now();
        var endDate = currentDate.plusMonths(MONTH_DUE);
        return memberDataSource.addMemberRecord(
                new MemberRecord(
                        PATH + System.currentTimeMillis(),
                        data.libraryMember(),
                        data.book(),
                        LocalDate.now(),
                        endDate,
                        null
                )
        );
    }
}
