package com.cesarwillymc.libraryprojectmpp.usecase.memberRecord;

import com.cesarwillymc.libraryprojectmpp.data.source.memberRecord.MemberRecordDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseParams;

import java.time.LocalDate;

public class UpdateMemberRecordUseCase extends UseCaseParams<String, Resource<Void>> {
    final private MemberRecordDataSource memberDataSource;

    public UpdateMemberRecordUseCase(MemberRecordDataSource memberDataSource) {
        this.memberDataSource = memberDataSource;
    }

    @Override
    public Resource<Void> run(String data) {

       var memberRecord= memberDataSource.getMembersRecordById(data);
       if (memberRecord.isSuccess()){
           var currentDate = LocalDate.now();
           var currentMember= memberRecord.getData();
           currentMember.setDateReturned(currentDate);
           return memberDataSource.updateMemberRecord(currentMember);
       }else {
           return Resource.Error(new Exception("Error trying to change"));
       }

    }
}
