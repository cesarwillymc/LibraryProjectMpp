package com.cesarwillymc.libraryprojectmpp.usecase.memberRecord;

import com.cesarwillymc.libraryprojectmpp.data.source.customer.BookDataSource;
import com.cesarwillymc.libraryprojectmpp.data.source.customer.BookFactoryDataSource;
import com.cesarwillymc.libraryprojectmpp.data.source.memberRecord.MemberRecordDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseParams;

import java.time.LocalDate;

public class UpdateMemberRecordUseCase extends UseCaseParams<String, Resource<Void>> {
    final private MemberRecordDataSource memberDataSource;
    final private BookDataSource bookDataSource;

    public UpdateMemberRecordUseCase(MemberRecordDataSource memberDataSource, BookDataSource bookDataSource) {
        this.memberDataSource = memberDataSource;
        this.bookDataSource = bookDataSource;
    }

    @Override
    public Resource<Void> run(String data) {

       var memberRecord= memberDataSource.getMembersRecordById(data);

       if (memberRecord.isSuccess()){
           var book = bookDataSource.getBookById(memberRecord.getData().getBook().getBookISBN()).getData();
           book.getCopy(memberRecord.getData().getBook().getCopyNum()).changeAvailability();
           bookDataSource.updateBook(book);
           var currentDate = LocalDate.now();
           var currentMember= memberRecord.getData();
           currentMember.setDateReturned(currentDate);

           return memberDataSource.updateMemberRecord(currentMember);
       }else {
           return Resource.Error(new Exception("Error trying to change"));
       }

    }
}
