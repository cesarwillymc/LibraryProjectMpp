package com.cesarwillymc.libraryprojectmpp.data.source.account.mapper;

import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.LibraryMemberResponse;
import com.cesarwillymc.libraryprojectmpp.data.utils.mapper.Mapper;
import com.cesarwillymc.libraryprojectmpp.domain.entities.LibraryMember;

public class MemberMapper extends Mapper<LibraryMember, LibraryMemberResponse> {
    private AddressMapper addressMapper;

    public MemberMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public LibraryMemberResponse domainToData(LibraryMember data) {
        return new LibraryMemberResponse(
                data.getMemberId(),
                data.getFirstName(),
                data.getLastName(),
                data.getTelephone(),
                addressMapper.domainToData(data.getAddress())
        );
    }
    @Override
    public LibraryMember dataToDomain(LibraryMemberResponse data) {
        return new LibraryMember(
                data.getMemberId(),
                data.getFirstName(),
                data.getLastName(),
                data.getTelephone(),
                addressMapper.dataToDomain(data.getAddress())
        );
    }
}
