package com.cesarwillymc.libraryprojectmpp.data.source.account.mapper;

import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.AddressResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.LibraryMemberResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.mapper.Mapper;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Address;
import com.cesarwillymc.libraryprojectmpp.domain.entities.LibraryMember;

public class MemberMapper extends Mapper<LibraryMember, LibraryMemberResponse> {
    @Override
    public LibraryMemberResponse domainToData(LibraryMember data) {
        return new LibraryMemberResponse(
                data.getMemberId(),
                data.getFirstName(),
                data.getLastName(),
                data.getTelephone(),
                from(data.getAddress())
        );
    }

    private AddressResponse from(Address data){
        return new AddressResponse(
                data.getStreet(),
                data.getCity(),
                data.getState(),
                data.getZip()
        );
    }
    private Address from(AddressResponse data){
        return new Address(
                data.street(),
                data.city(),
                data.state(),
                data.zip()
        );
    }

    @Override
    public LibraryMember dataToDomain(LibraryMemberResponse data) {
        return new LibraryMember(
                data.getMemberId(),
                data.getFirstName(),
                data.getLastName(),
                data.getTelephone(),
                from(data.getAddress())
        );
    }
}
