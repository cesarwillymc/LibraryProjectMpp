package com.cesarwillymc.libraryprojectmpp.ui.books.view;

import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.time.format.DateTimeFormatter;

public class BookCard extends VBox {

    final Book book;
    public BookCard(Book book) {
        this.book = book;
        // Set up card layout
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10));
        setSpacing(10);
      //  setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px;");

        // Add card content
        Label titleLabel = new Label(book.getTitle());
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label isbnLabel = new Label("ISBN: " + book.getIsbn());
        Label dateCreatedLabel = new Label("Date created: " + book.getDateCreated().format(DateTimeFormatter.ISO_DATE));
        Label numCopiesLabel = new Label("Number of copies: " + book.getNumCopies());

        getChildren().addAll(titleLabel, isbnLabel, dateCreatedLabel, numCopiesLabel);
    }

    public Book getBook() {
        return book;
    }
}
