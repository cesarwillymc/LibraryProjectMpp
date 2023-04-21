package com.cesarwillymc.libraryprojectmpp.data.source.customer.mapper;

import com.cesarwillymc.libraryprojectmpp.data.source.customer.entity.BookCopyResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.customer.entity.BookResponse;
import com.cesarwillymc.libraryprojectmpp.data.utils.mapper.Mapper;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;
import com.cesarwillymc.libraryprojectmpp.domain.entities.BookCopy;
import java.util.Arrays;

public class BookDataMapper extends Mapper<Book, BookResponse> {
    @Override
    public BookResponse domainToData(Book data) {
        return new BookResponse(
                from(data.getCopies()),
                data.getAuthors(),
                data.getIsbn(),
                data.getTitle(),
                data.getMaxCheckoutLength(),
                data.getDateCreated(),
                data.getDateUpdated()
        );
    }

    private BookCopyResponse[] from(BookCopy[] value) {
        return Arrays.stream(value).map(x ->
                new BookCopyResponse(
                        domainToData(x.getBook()),
                        x.getCopyNum(),
                        x.isAvailable()
                )).toArray(BookCopyResponse[]::new);
    }

    private BookCopy[] from(BookCopyResponse[] value) {
        return Arrays.stream(value).map(x ->
                new BookCopy(
                        dataToDomain(x.book()),
                        x.copyNum(),
                        x.isAvailable()
                )).toArray(BookCopy[]::new);
    }

    @Override
    public Book dataToDomain(BookResponse data) {
        return new Book(
                from(data.copies()),
                data.authors(),
                data.isbn(),
                data.title(),
                data.maxCheckoutLength(),
                data.dateCreated(),
                data.dateUpdated()
        );
    }

}
