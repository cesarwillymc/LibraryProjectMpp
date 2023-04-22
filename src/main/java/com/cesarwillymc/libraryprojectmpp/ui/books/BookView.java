package com.cesarwillymc.libraryprojectmpp.ui.books;

import com.almasb.fxgl.core.View;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;
import com.cesarwillymc.libraryprojectmpp.ui.books.controller.BookController;
import com.cesarwillymc.libraryprojectmpp.ui.books.view.BookCard;
import com.cesarwillymc.libraryprojectmpp.ui.di.DIControllers;
import com.cesarwillymc.libraryprojectmpp.ui.view.ButtonCard;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class BookView implements View {
    private final Node node;

    private final BookController controller = DIControllers.createBookController();

    public BookView(Runnable navigateAddBook, Consumer<Book> navigateBookDetail) {
        // Create a BorderPane to hold all the components
        ListView<BookCard> cardList = new ListView<>(FXCollections.observableArrayList());

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Create a VBox to hold the search components
        VBox searchBox = new VBox();
        searchBox.setSpacing(10);
        root.setTop(searchBox);

        // Create the search TextField and Button
        TextField searchField = new TextField();
        searchField.setPromptText("Search by ISBN and Title");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            controller.searchInList(searchField.getText(), (x) -> {
                cardList.setItems(FXCollections.observableArrayList(x));
            });
        });
        Button addNewMemberButton = new Button("Add New Book");
        Button refreshList = new Button("Refresh List");
        refreshList.setOnAction(event -> controller.getInitialListMembers((x) -> {
            cardList.setItems(FXCollections.observableArrayList(x));
        }));
        addNewMemberButton.setOnAction(event -> navigateAddBook.run());
        HBox searchControls = new HBox(searchField, searchButton, refreshList, addNewMemberButton);
        searchControls.setAlignment(Pos.CENTER_LEFT);
        searchControls.setSpacing(10);
        searchBox.getChildren().add(searchControls);

        // Create the search filters
        HBox filterBox = new HBox();
        filterBox.setSpacing(10);
        searchBox.getChildren().add(filterBox);

        ButtonCard filter1 = new ButtonCard("Title", (b) -> {
            controller.filterByTitle(b, (x) -> {
                cardList.setItems(FXCollections.observableArrayList(x));
            });
        });
        ButtonCard filter2 = new ButtonCard("Date created", (b) -> {
            controller.filterByDateCreated(b, (x) -> {
                cardList.setItems(FXCollections.observableArrayList(x));
            });
        });
        ButtonCard filter3 = new ButtonCard("Date updated", (b) -> {
            controller.filterByDateUpdated(b, (x) -> {
                cardList.setItems(FXCollections.observableArrayList(x));
            });
        });
        Button clearFilter = new Button("Clear filters");
        clearFilter.setOnAction(event -> {
            controller.cleanFilter((x) -> {
                cardList.setItems(FXCollections.observableArrayList(x));
            });
        });
        filterBox.getChildren().addAll(new Label("Filters: "), filter1, filter2, filter3, clearFilter);
        filterBox.setPadding(new Insets(10));
        // Create the ListView of cards
        cardList.setPrefSize(400, 400);
        cardList.setPadding(new Insets(20));
        cardList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // double-click
                var selectedItem = cardList.getSelectionModel().getSelectedItem();
                navigateBookDetail.accept(selectedItem.getBook());
                cardList.getSelectionModel().clearSelection();
            }
        });

        root.setCenter(cardList);
        this.node = root;

        controller.getInitialListMembers((x) -> {
            cardList.setItems(FXCollections.observableArrayList(x));
        });
    }

    @Override
    public Node getNode() {
        return node;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void onUpdate(double tpf) {

    }
}
