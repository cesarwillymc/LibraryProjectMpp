package com.cesarwillymc.libraryprojectmpp.data.source.account.entity;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityData;

@EntityData
final public class LibraryMemberResponse extends PersonResponse {
    private final String memberId;

    public LibraryMemberResponse(String memberId, String fname, String lname, String tel, AddressResponse add) {
        super(fname, lname, tel, add);
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }
}
