package com.cesarwillymc.libraryprojectmpp.data.source.memberRecord.entity;

import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.LibraryMemberResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.customer.entity.BookCopyResponse;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public record MemberRecordResponse(
        String id,
        LibraryMemberResponse memberResponse,
        BookCopyResponse book,
        LocalDate dateBorrow,
        LocalDate dateDue,
        LocalDate dateReturned
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1113799434508676095L;
}



