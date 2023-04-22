package com.cesarwillymc.libraryprojectmpp.ui.books.controller;

import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;
import com.cesarwillymc.libraryprojectmpp.ui.books.view.BookCard;
import com.cesarwillymc.libraryprojectmpp.usecase.book.GetAllBooksUseCase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class BookController {

    final GetAllBooksUseCase allBooksUseCase;
    private List<Book> books;

    public BookController(GetAllBooksUseCase allBooksUseCase) {
        this.allBooksUseCase = allBooksUseCase;

    }

    public void getInitialListMembers(Consumer<List<BookCard>> data) {
        allBooksUseCase.run().apply(x -> {
            books = x;
            data.accept(books.stream().map(this::from).collect(Collectors.toList()));
        }, e -> books = new ArrayList<>());
    }

    public void filterByTitle(boolean isReverse, Consumer<List<BookCard>> data) {
        var list = books.stream().sorted((isReverse) ?
                Comparator.comparing(Book::getTitle).reversed() :
                Comparator.comparing(Book::getTitle)

        ).map(this::from).collect(Collectors.toList());
        data.accept(list);
    }

    public void filterByDateCreated(boolean isReverse, Consumer<List<BookCard>> data) {
        var list = books.stream().sorted((isReverse) ?
                Comparator.comparing(Book::getDateCreated).reversed().thenComparing(Book::getTitle) :
                Comparator.comparing(Book::getDateCreated).thenComparing(Book::getTitle)

        ).map(this::from).collect(Collectors.toList());
        data.accept(list);
    }

    public void filterByDateUpdated(boolean isReverse, Consumer<List<BookCard>> data) {
        var list = books.stream().sorted((isReverse) ?
                Comparator.comparing(Book::getDateUpdated).reversed().thenComparing(Book::getTitle) :
                Comparator.comparing(Book::getDateUpdated).thenComparing(Book::getTitle)
        ).map(this::from).collect(Collectors.toList());
        data.accept(list);
    }

    public void searchInList(String word, Consumer<List<BookCard>> data) {
        var list = books.stream().filter(x ->
                x.getIsbn().contains(word) || x.getTitle().contains(word)
        ).map(this::from).collect(Collectors.toList());
        data.accept(list);
    }

    public void cleanFilter(Consumer<List<BookCard>> data) {
        data.accept(books.stream().map(this::from).collect(Collectors.toList()));
    }

    private BookCard from(Book book) {
        return new BookCard(
                book
        );
    }
}
