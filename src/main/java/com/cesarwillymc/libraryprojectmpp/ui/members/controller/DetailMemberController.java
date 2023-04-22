package com.cesarwillymc.libraryprojectmpp.ui.members.controller;

import com.cesarwillymc.libraryprojectmpp.domain.entities.LibraryMember;
import com.cesarwillymc.libraryprojectmpp.domain.entities.MemberRecord;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.member.GetMemberByIdUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.memberRecord.GetMemberRecordByUserIdUseCase;

import java.util.List;

public class DetailMemberController {
    GetMemberByIdUseCase getMemberByIdUseCase;
    GetMemberRecordByUserIdUseCase getMemberRecordByUserIdUseCase;

    public DetailMemberController(GetMemberByIdUseCase getMemberByIdUseCase, GetMemberRecordByUserIdUseCase getMemberRecordByUserIdUseCase) {
        this.getMemberByIdUseCase = getMemberByIdUseCase;
        this.getMemberRecordByUserIdUseCase = getMemberRecordByUserIdUseCase;
    }

    public Resource<LibraryMember> getUserById(String id){
        return getMemberByIdUseCase.run(id);
    }
    public Resource<List<MemberRecord>> getRecordMembers(String id){
        return getMemberRecordByUserIdUseCase.run(id);
    }
}
