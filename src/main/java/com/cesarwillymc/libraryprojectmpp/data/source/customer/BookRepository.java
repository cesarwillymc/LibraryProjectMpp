package com.cesarwillymc.libraryprojectmpp.data.source.customer;

import com.cesarwillymc.libraryprojectmpp.data.local.DataAccessDao;
import com.cesarwillymc.libraryprojectmpp.data.source.customer.mapper.BookDataMapper;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;
import com.cesarwillymc.libraryprojectmpp.domain.exception.LibrarySystemException;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;

import java.util.List;

class BookRepository implements BookDataSource {
    private final DataAccessDao dataAccess;
    private final BookDataMapper mapper;

    BookRepository(DataAccessDao dataAccess, BookDataMapper mapper) {
        this.dataAccess = dataAccess;
        this.mapper = mapper;
    }

    @Override
    public Resource<List<Book>> getAllBooks() {
        return Resource.Sucess(mapper.dataToDomain(dataAccess.getAllBooks()));
    }

    @Override
    public Resource<Book> getBookById(String id) {
        try {
            return Resource.Sucess(mapper.dataToDomain(dataAccess.getBookById(id)));
        } catch (LibrarySystemException e) {
            return Resource.Error(e);
        }
    }

    @Override
    public Resource<Void> addBook(Book bookResponse) {
        try {
            dataAccess.addBook(mapper.domainToData(bookResponse));
            return Resource.Sucess(null);
        } catch (LibrarySystemException e) {
            return Resource.Error(e);
        }
    }

    @Override
    public Resource<Void> updateBook(Book bookResponse) {
        try {
            dataAccess.updateBook(mapper.domainToData(bookResponse));
            return Resource.Sucess(null);
        } catch (LibrarySystemException e) {
            return Resource.Error(e);
        }
    }

    @Override
    public Resource<Void> removeBook(String id) {
        try {
            dataAccess.removeBook(id);
            return Resource.Sucess(null);
        } catch (LibrarySystemException e) {
            return Resource.Error(e);
        }
    }
}
