package com.cesarwillymc.libraryprojectmpp.data.source.account.entity;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityData;

import java.io.Serializable;

@EntityData
final public class LibraryMemberResponse extends PersonResponse implements Serializable {
    private static final long serialVersionUID = 111379943451898769L;
    private final String memberId;

    public LibraryMemberResponse(String memberId, String fname, String lname, String tel, AddressResponse add) {
        super(fname, lname, tel, add);
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }
}
