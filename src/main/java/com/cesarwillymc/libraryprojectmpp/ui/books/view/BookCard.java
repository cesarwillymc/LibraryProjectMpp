package com.cesarwillymc.libraryprojectmpp.ui.books.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class BookCard extends VBox {

    private String isbn;
    private String title;
    private LocalDate dateCreated;
    private LocalDate dateUpdated;
    private Integer numOfCopies;

    public BookCard(String isbn, String title, LocalDate dateCreated,
                LocalDate dateUpdated, Integer numOfCopies) {

        this.isbn = isbn;
        this.title = title;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.numOfCopies = numOfCopies;

        // Set up card layout
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10));
        setSpacing(10);
      //  setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px;");

        // Add card content
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label isbnLabel = new Label("ISBN: " + isbn);
        Label dateCreatedLabel = new Label("Date created: " + dateCreated.format(DateTimeFormatter.ISO_DATE));
        Label dateUpdatedLabel = new Label("Date updated: " + dateUpdated.format(DateTimeFormatter.ISO_DATE));
        Label numCopiesLabel = new Label("Number of copies: " + numOfCopies);

        getChildren().addAll(titleLabel, isbnLabel, dateCreatedLabel, dateUpdatedLabel, numCopiesLabel);
    }

    public String getIsbn() {
        return isbn;
    }
}
