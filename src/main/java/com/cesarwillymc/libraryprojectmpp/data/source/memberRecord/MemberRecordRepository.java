package com.cesarwillymc.libraryprojectmpp.data.source.memberRecord;

import com.cesarwillymc.libraryprojectmpp.data.local.DataAccessDao;
import com.cesarwillymc.libraryprojectmpp.data.source.memberRecord.mapper.MemberRecordMapper;
import com.cesarwillymc.libraryprojectmpp.domain.entities.MemberRecord;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;

import java.util.List;

public class MemberRecordRepository implements MemberRecordDataSource {
    private final DataAccessDao dataAccess;
    private final MemberRecordMapper mapper;

    public MemberRecordRepository(DataAccessDao dataAccess, MemberRecordMapper mapper) {
        this.dataAccess = dataAccess;
        this.mapper = mapper;
    }


    @Override
    public Resource<Void> addMemberRecord(MemberRecord recordResponse) {
        try {
            dataAccess.addMemberRecord(mapper.domainToData(recordResponse));
            return Resource.Sucess(null);
        } catch (Exception e) {
            return Resource.Error(e);
        }
    }

    @Override
    public Resource<Void> updateMemberRecord(MemberRecord recordResponse) {
        try {
            dataAccess.updateMemberRecord(mapper.domainToData(recordResponse));
            return Resource.Sucess(null);
        } catch (Exception e) {
            return Resource.Error(e);
        }
    }

    @Override
    public Resource<List<MemberRecord>> getMembersRecordsByUserId(String id) {
        try {
            var data = dataAccess.getAllMembersRecord().stream().filter(x -> x.memberResponse().getMemberId().equals(id)).toList();
            return Resource.Sucess(mapper.dataToDomain(data));
        } catch (Exception e) {
            return Resource.Error(e);
        }
    }

    @Override
    public Resource<List<MemberRecord>> getMembersRecordByBookId(String id) {
        try {
            var data = dataAccess.getAllMembersRecord().stream().filter(x -> x.book().isbn().equals(id)).toList();
            return Resource.Sucess(mapper.dataToDomain(data));
        } catch (Exception e) {
            return Resource.Error(e);
        }
    }

    @Override
    public Resource<MemberRecord> getMembersRecordById(String id) {
        try {
            var data = dataAccess.getAllMembersRecord().stream().filter(x -> x.id().equals(id)).findFirst();
            return Resource.Sucess(mapper.dataToDomain(data.get()));
        } catch (Exception e) {
            return Resource.Error(e);
        }
    }

    @Override
    public Resource<List<MemberRecord>> getAllMemberRecord() {
        try {
            var data = dataAccess.getAllMembersRecord();
            return Resource.Sucess(mapper.dataToDomain(data));
        } catch (Exception e) {
            return Resource.Error(e);
        }
    }
}
