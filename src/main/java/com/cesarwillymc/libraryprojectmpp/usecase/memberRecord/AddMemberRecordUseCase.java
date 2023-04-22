package com.cesarwillymc.libraryprojectmpp.usecase.memberRecord;

import com.cesarwillymc.libraryprojectmpp.data.source.customer.BookDataSource;
import com.cesarwillymc.libraryprojectmpp.data.source.memberRecord.MemberRecordDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.entities.MemberRecord;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.memberRecord.entity.MemberRecordParams;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseParams;

import java.time.LocalDate;

public class AddMemberRecordUseCase extends UseCaseParams<MemberRecordParams, Resource<Void>> {
    final private MemberRecordDataSource memberDataSource;
    final private BookDataSource bookDataSource;
    private final static String PATH = "book";
    private final static Integer MONTH_DUE = 1;

    public AddMemberRecordUseCase(MemberRecordDataSource memberDataSource, BookDataSource bookDataSource) {
        this.memberDataSource = memberDataSource;
        this.bookDataSource = bookDataSource;
    }

    @Override
    public Resource<Void> run(MemberRecordParams data) {
        try {
            var currentDate = LocalDate.now();
            var endDate = currentDate.plusMonths(MONTH_DUE);
            var book = data.book();

            var nextBook = book.getNextAvailableCopy();
            nextBook.changeAvailability();
            bookDataSource.updateBook(book);
            return memberDataSource.addMemberRecord(
                    new MemberRecord(
                            PATH + System.currentTimeMillis(),
                            data.libraryMember(),
                            nextBook,
                            LocalDate.now(),
                            endDate,
                            null
                    )
            );
        } catch (Exception e) {
            return Resource.Error(e);
        }
    }
}
