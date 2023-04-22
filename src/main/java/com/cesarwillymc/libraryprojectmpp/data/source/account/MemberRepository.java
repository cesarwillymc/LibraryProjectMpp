package com.cesarwillymc.libraryprojectmpp.data.source.account;

import com.cesarwillymc.libraryprojectmpp.data.local.DataAccessDao;
import com.cesarwillymc.libraryprojectmpp.data.source.account.mapper.MemberMapper;
import com.cesarwillymc.libraryprojectmpp.domain.entities.LibraryMember;
import com.cesarwillymc.libraryprojectmpp.domain.exception.LibrarySystemException;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;

import java.util.List;

class MemberRepository implements MemberDataSource {
    private final DataAccessDao dataAccess;
    private final MemberMapper mapper;

    MemberRepository(DataAccessDao dataAccess, MemberMapper mapper) {
        this.dataAccess = dataAccess;
        this.mapper = mapper;
    }

    @Override
    public Resource<Void> addNewMember(LibraryMember libraryMember) {
        try {
            dataAccess.addMember(mapper.domainToData(libraryMember));
            return Resource.Sucess(null);
        } catch (LibrarySystemException e) {
            return Resource.Error(e);
        }
    }

    @Override
    public Resource<List<LibraryMember>> getAllMembers() {
        try {
            return Resource.Sucess(mapper.dataToDomain(dataAccess.getAllMembers()));
        } catch (LibrarySystemException e) {
            return Resource.Error(e);
        }
    }

    @Override
    public Resource<LibraryMember> getMemberById(String id) {
        try {
            return Resource.Sucess(mapper.dataToDomain(dataAccess.getMemberById(id)));
        } catch (LibrarySystemException e) {
            return Resource.Error(e);
        }
    }

    @Override
    public Resource<Void> removeMember(String id) {
        try {
            dataAccess.removeMember(id);
            return Resource.Sucess(null);
        } catch (LibrarySystemException e) {
            return Resource.Error(e);
        }
    }
}
