package com.cesarwillymc.libraryprojectmpp.usecase.book;

import com.cesarwillymc.libraryprojectmpp.data.source.customer.BookDataSource;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.util.UseCaseNoParams;

import java.util.List;

public class GetAllBooksUseCase extends UseCaseNoParams<Resource<List<Book>>> {
    final private BookDataSource bookDataSource;

    public GetAllBooksUseCase(BookDataSource bookDataSource) {
        this.bookDataSource = bookDataSource;
    }
    @Override
    public Resource<List<Book>> run() {
        return bookDataSource.getAllBooks();
    }
}
