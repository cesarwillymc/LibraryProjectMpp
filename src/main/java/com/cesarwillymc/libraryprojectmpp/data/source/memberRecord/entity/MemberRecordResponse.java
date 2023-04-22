package com.cesarwillymc.libraryprojectmpp.data.source.memberRecord.entity;

import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.LibraryMemberResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.customer.entity.BookResponse;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;

import java.io.Serializable;
import java.time.LocalDate;

public record MemberRecordResponse(
        String id,
        LibraryMemberResponse memberResponse,
        BookResponse book,
        LocalDate dateBorrow,
        LocalDate dateDue,
        LocalDate dateReturned
) implements Serializable {
    private static final long serialVersionUID = 1113799434508676095L;
}



