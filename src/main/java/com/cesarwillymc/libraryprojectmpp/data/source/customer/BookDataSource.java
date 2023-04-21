package com.cesarwillymc.libraryprojectmpp.data.source.customer;

import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;

import java.util.List;

public interface BookDataSource {
    Resource<List<Book>> getAllBooks();

    Resource<Book> getBookById(String id);

    Resource<Void> addBook(Book book);

    Resource<Void> updateBook(Book book);

    Resource<Void> removeBook(String id);
}
