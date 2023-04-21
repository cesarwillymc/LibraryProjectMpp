package com.cesarwillymc.libraryprojectmpp.data.source.customer;

import com.cesarwillymc.libraryprojectmpp.data.local.DataAccessDao;
import com.cesarwillymc.libraryprojectmpp.data.source.customer.mapper.BookDataMapper;

public class BookFactoryDataSource {
    public static BookDataSource getBookDataSource(DataAccessDao access) {
        return new BookRepository(access, new BookDataMapper());
    }
}
