package com.cesarwillymc.libraryprojectmpp.ui.books.controller;

import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;
import com.cesarwillymc.libraryprojectmpp.domain.entities.MemberRecord;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.book.UpdateBookUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.memberRecord.GetMemberRecordByBookIdUseCase;

import java.util.List;

public class BookDetailController {
    UpdateBookUseCase updateBookUseCase;
    GetMemberRecordByBookIdUseCase getMemberRecordByBookIdUseCase;

    public BookDetailController(UpdateBookUseCase updateBookUseCase, GetMemberRecordByBookIdUseCase getMemberRecordByBookIdUseCase) {
        this.updateBookUseCase = updateBookUseCase;
        this.getMemberRecordByBookIdUseCase = getMemberRecordByBookIdUseCase;
    }

    public Resource<Void> updateNumOfCopies(Book book) {
        return updateBookUseCase.run(book);
    }

    public Resource<List<MemberRecord>> getMemberRecordsById(String id) {
        return getMemberRecordByBookIdUseCase.run(id);
    }
}
