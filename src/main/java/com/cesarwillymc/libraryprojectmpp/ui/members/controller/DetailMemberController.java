package com.cesarwillymc.libraryprojectmpp.ui.members.controller;

import com.cesarwillymc.libraryprojectmpp.domain.entities.LibraryMember;
import com.cesarwillymc.libraryprojectmpp.domain.entities.MemberRecord;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.member.GetMemberByIdUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.memberRecord.GetMemberRecordByUserIdUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.memberRecord.UpdateMemberRecordUseCase;

import java.util.List;

public class DetailMemberController {
    final GetMemberByIdUseCase getMemberByIdUseCase;
    final GetMemberRecordByUserIdUseCase getMemberRecordByUserIdUseCase;

    final UpdateMemberRecordUseCase updateMemberRecordUseCase;

    public DetailMemberController(GetMemberByIdUseCase getMemberByIdUseCase, GetMemberRecordByUserIdUseCase getMemberRecordByUserIdUseCase, UpdateMemberRecordUseCase updateMemberRecordUseCase) {
        this.getMemberByIdUseCase = getMemberByIdUseCase;
        this.getMemberRecordByUserIdUseCase = getMemberRecordByUserIdUseCase;
        this.updateMemberRecordUseCase = updateMemberRecordUseCase;
    }

    public Resource<LibraryMember> getUserById(String id){
        return getMemberByIdUseCase.run(id);
    }
    public Resource<List<MemberRecord>> getRecordMembers(String id){
        return getMemberRecordByUserIdUseCase.run(id);
    }
    public Resource<Void> updateMemberRecord(String id){
        return updateMemberRecordUseCase.run(id);
    }

}
