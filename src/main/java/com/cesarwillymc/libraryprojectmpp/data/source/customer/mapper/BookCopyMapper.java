package com.cesarwillymc.libraryprojectmpp.data.source.customer.mapper;

import com.cesarwillymc.libraryprojectmpp.data.source.customer.entity.BookCopyResponse;
import com.cesarwillymc.libraryprojectmpp.domain.entities.BookCopy;

import java.util.Arrays;

public class BookCopyMapper {

    public BookCopyResponse domainToData(BookCopy data) {
        return new BookCopyResponse(
                data.getBookISBN(),
                data.getName(),
                data.getCopyNum(),
                data.isAvailable()
        );
    }


    public BookCopy dataToDomain(BookCopyResponse data) {
        return new BookCopy(
                data.isbn(),
                data.name(),
                data.copyNum(),
                data.isAvailable()
        );
    }

    public BookCopyResponse[] domainToData(BookCopy[] value) {
        return Arrays.stream(value).map(this::domainToData
        ).toArray(BookCopyResponse[]::new);
    }

    public BookCopy[] dataToDomain(BookCopyResponse[] value) {
        return Arrays.stream(value).map(this::dataToDomain
        ).toArray(BookCopy[]::new);
    }
}
