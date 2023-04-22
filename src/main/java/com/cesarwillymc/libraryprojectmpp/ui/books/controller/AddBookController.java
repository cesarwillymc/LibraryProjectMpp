package com.cesarwillymc.libraryprojectmpp.ui.books.controller;

import com.cesarwillymc.libraryprojectmpp.domain.entities.Author;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.book.AddBookUseCase;

import java.util.List;

public class AddBookController {
    AddBookUseCase addBookUseCase;

    public AddBookController(AddBookUseCase addBookUseCase) {
        this.addBookUseCase = addBookUseCase;
    }

    public Resource<Void> saveBook(String isbn, Integer numOfCopies, String title, List<Author> authorList) {
        return addBookUseCase.run(new Book(isbn, title, authorList, numOfCopies));
    }
}
