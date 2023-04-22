package com.cesarwillymc.libraryprojectmpp.data.source.customer.mapper;

import com.cesarwillymc.libraryprojectmpp.data.source.customer.entity.BookResponse;
import com.cesarwillymc.libraryprojectmpp.data.utils.mapper.Mapper;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;

import java.util.Map;

public class BookDataMapper extends Mapper<Book,BookResponse> {
    BookCopyMapper mapper;

    public BookDataMapper(BookCopyMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BookResponse domainToData(Book data) {
        return new BookResponse(
                mapper.domainToData(data.getCopies()),
                data.getAuthors(),
                data.getIsbn(),
                data.getTitle(),
                data.getDateCreated(),
                data.getDateUpdated()
        );
    }

    @Override
    public Book dataToDomain(BookResponse data) {
        return new Book(
                mapper.dataToDomain(data.copies()),
                data.authors(),
                data.isbn(),
                data.title(),
                data.dateCreated(),
                data.dateUpdated()
        );
    }

}
