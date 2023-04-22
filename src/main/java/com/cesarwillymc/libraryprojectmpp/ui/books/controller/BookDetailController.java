package com.cesarwillymc.libraryprojectmpp.ui.books.controller;

import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;
import com.cesarwillymc.libraryprojectmpp.domain.entities.MemberRecord;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.book.UpdateBookUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.memberRecord.GetMemberRecordByBookIdUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.memberRecord.UpdateMemberRecordUseCase;

import java.util.List;

public class BookDetailController {
    final UpdateBookUseCase updateBookUseCase;
    final GetMemberRecordByBookIdUseCase getMemberRecordByBookIdUseCase;
    final UpdateMemberRecordUseCase updateMemberRecordUseCase;

    public BookDetailController(UpdateBookUseCase updateBookUseCase, GetMemberRecordByBookIdUseCase getMemberRecordByBookIdUseCase, UpdateMemberRecordUseCase updateMemberRecordUseCase) {
        this.updateBookUseCase = updateBookUseCase;
        this.getMemberRecordByBookIdUseCase = getMemberRecordByBookIdUseCase;
        this.updateMemberRecordUseCase = updateMemberRecordUseCase;
    }

    public Resource<Void> updateNumOfCopies(Book book) {
        return updateBookUseCase.run(book);
    }

    public Resource<List<MemberRecord>> getMemberRecordsById(String id) {
        return getMemberRecordByBookIdUseCase.run(id);
    }
    public Resource<Void> updateMemberRecord(String id){
        return updateMemberRecordUseCase.run(id);
    }
}
