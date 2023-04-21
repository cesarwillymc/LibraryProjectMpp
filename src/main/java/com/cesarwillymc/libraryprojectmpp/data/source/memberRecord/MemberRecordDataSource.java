package com.cesarwillymc.libraryprojectmpp.data.source.memberRecord;

import com.cesarwillymc.libraryprojectmpp.data.source.memberRecord.entity.MemberRecordResponse;
import com.cesarwillymc.libraryprojectmpp.domain.entities.MemberRecord;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;

import java.util.List;

public interface MemberRecordDataSource {

    Resource<Void> addMemberRecord(MemberRecord recordResponse);

    Resource<Void> updateMemberRecord(MemberRecord recordResponse);

    Resource<List<MemberRecord>> getMembersRecordsByUserId(String id);

    Resource<List<MemberRecord>> getMembersRecordByBookId(String id);
    Resource<List<MemberRecord>> getAllMemberRecord();
}
