package com.cesarwillymc.libraryprojectmpp.usecase.book;

import com.cesarwillymc.libraryprojectmpp.data.source.customer.BookDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseParams;

public class UpdateBookUseCase extends UseCaseParams<Book, Resource<Void>> {
    final private BookDataSource bookDataSource;

    public UpdateBookUseCase(BookDataSource bookDataSource) {
        this.bookDataSource = bookDataSource;
    }
    public Resource<Void> run(Book libraryMember) {
        return bookDataSource.updateBook(libraryMember);
    }
}
