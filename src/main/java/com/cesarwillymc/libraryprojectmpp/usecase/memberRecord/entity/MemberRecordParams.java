package com.cesarwillymc.libraryprojectmpp.usecase.memberRecord.entity;

import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;
import com.cesarwillymc.libraryprojectmpp.domain.entities.LibraryMember;

public record MemberRecordParams(
        Book book,
        LibraryMember libraryMember
) {
}
