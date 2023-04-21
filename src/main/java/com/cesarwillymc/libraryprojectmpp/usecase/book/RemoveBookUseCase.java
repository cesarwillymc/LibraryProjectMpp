package com.cesarwillymc.libraryprojectmpp.usecase.book;

import com.cesarwillymc.libraryprojectmpp.data.source.customer.BookDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseParams;

public class RemoveBookUseCase extends UseCaseParams<String,Resource<Void>> {
    final private BookDataSource bookDataSource;

    public RemoveBookUseCase(BookDataSource bookDataSource) {
        this.bookDataSource = bookDataSource;
    }
    @Override
    public Resource<Void> run(String id) {
        return bookDataSource.removeBook(id);
    }
}
