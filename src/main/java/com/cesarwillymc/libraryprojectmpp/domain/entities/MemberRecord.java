package com.cesarwillymc.libraryprojectmpp.domain.entities;

import com.cesarwillymc.libraryprojectmpp.domain.enums.TypeStatusReturnBook;

import java.time.LocalDate;

public class MemberRecord {
    private String id;
    private LibraryMember memberResponse;
    private BookCopy book;
    private LocalDate dateBorrow;
    private LocalDate dateDue;
    private LocalDate dateReturned;

    public MemberRecord(String id, LibraryMember memberResponse, BookCopy book, LocalDate dateBorrow, LocalDate dateDue, LocalDate dateReturned) {
        this.id = id;
        this.memberResponse = memberResponse;
        this.book = book;
        this.dateBorrow = dateBorrow;
        this.dateDue = dateDue;
        this.dateReturned = dateReturned;
    }

    public String getId() {
        return id;
    }

    public LibraryMember getMemberResponse() {
        return memberResponse;
    }

    public BookCopy getBook() {
        return book;
    }

    public LocalDate getDateBorrow() {
        return dateBorrow;
    }

    public void setDateBorrow(LocalDate dateBorrow) {
        this.dateBorrow = dateBorrow;
    }

    public LocalDate getDateDue() {
        return dateDue;
    }

    public void setDateDue(LocalDate dateDue) {
        this.dateDue = dateDue;
    }

    public LocalDate getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }

    public Boolean getWasReturned() {
        return dateReturned != null;
    }

    public TypeStatusReturnBook getStatusReturnBook() {
        if (dateReturned != null)
            return TypeStatusReturnBook.FINISH;
        if (LocalDate.now().isAfter(dateDue))
            return TypeStatusReturnBook.OUT_TIME;
        return TypeStatusReturnBook.PROCESS;
    }
}
