package com.cesarwillymc.libraryprojectmpp.usecase.book;

import com.cesarwillymc.libraryprojectmpp.data.source.customer.BookDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseParams;

public class GetBookByIdUseCase extends UseCaseParams<String, Resource<Book>> {
    final private BookDataSource bookDataSource;

    public GetBookByIdUseCase(BookDataSource bookDataSource) {
        this.bookDataSource = bookDataSource;
    }
    @Override
    public Resource<Book> run(String id) {
        return bookDataSource.getBookById(id);
    }
}
