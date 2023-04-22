package com.cesarwillymc.libraryprojectmpp.ui.checkoutBook.controller;

import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;
import com.cesarwillymc.libraryprojectmpp.domain.entities.LibraryMember;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.book.GetBookByIdUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.member.GetMemberByIdUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.memberRecord.AddMemberRecordUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.memberRecord.entity.MemberRecordParams;

public class CheckoutController {
    final GetMemberByIdUseCase getMemberByIdUseCase;
    final GetBookByIdUseCase getBookByIdUseCase;
    final AddMemberRecordUseCase addMemberRecordUseCase;

    public CheckoutController(GetMemberByIdUseCase getMemberByIdUseCase, GetBookByIdUseCase getBookByIdUseCase, AddMemberRecordUseCase addMemberRecordUseCase) {
        this.getMemberByIdUseCase = getMemberByIdUseCase;
        this.getBookByIdUseCase = getBookByIdUseCase;
        this.addMemberRecordUseCase = addMemberRecordUseCase;
    }

    public LibraryMember libraryMember;
    public Book book;

    public Resource<LibraryMember> searchMemberById(String id) {
        return getMemberByIdUseCase.run(id);
    }

    public Resource<Book> searchBookById(String id) {
        return getBookByIdUseCase.run(id);
    }

    public Resource<Void> saveMemberCheckout() {
        return addMemberRecordUseCase.run(new MemberRecordParams(book, libraryMember));
    }

    public LibraryMember getLibraryMember() {
        return libraryMember;
    }

    public void setLibraryMember(LibraryMember libraryMember) {
        this.libraryMember = libraryMember;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
