package com.cesarwillymc.libraryprojectmpp.ui.checkoutBook;

import com.almasb.fxgl.core.View;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;
import com.cesarwillymc.libraryprojectmpp.domain.entities.LibraryMember;
import com.cesarwillymc.libraryprojectmpp.ui.books.view.BookCard;
import com.cesarwillymc.libraryprojectmpp.ui.checkoutBook.controller.CheckoutController;
import com.cesarwillymc.libraryprojectmpp.ui.di.DIControllers;
import com.cesarwillymc.libraryprojectmpp.ui.members.view.MemberCard;
import com.cesarwillymc.libraryprojectmpp.ui.view.DialogError;
import com.cesarwillymc.libraryprojectmpp.ui.view.DialogSuccess;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CheckoutBookView implements View {
    private final Node node;
    private final TextField isbnField;
    private final TextField userIdField;
    final HBox hBoxCardUser = new HBox();
    final HBox hBoxCardBook = new HBox();
    final CheckoutController controller = DIControllers.createCheckoutController();

    public CheckoutBookView() {
        VBox root = new VBox();
        hBoxCardBook.setAlignment(Pos.CENTER);
        hBoxCardUser.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        HBox isbnBox = new HBox();
        isbnBox.setAlignment(Pos.CENTER_LEFT);
        isbnBox.setSpacing(10);
        Label isbnLabel = new Label("Search book by ISBN:");
        isbnField = new TextField();
        Button searchBookButton = new Button("Search");
        isbnBox.getChildren().addAll(isbnLabel, isbnField, searchBookButton);

        HBox userIdBox = new HBox();
        userIdBox.setAlignment(Pos.CENTER_LEFT);
        userIdBox.setSpacing(10);
        Label userIdLabel = new Label("Search user by ID:");
        userIdField = new TextField();
        Button searchUserButton = new Button("Search");
        userIdBox.getChildren().addAll(userIdLabel, userIdField, searchUserButton);


        Button saveCheckoutButton = new Button("save CheckOut");


        root.getChildren().addAll(isbnBox, hBoxCardBook, userIdBox, hBoxCardUser, saveCheckoutButton);

        node = root;

        searchBookButton.setOnAction(event -> {
            if (isbnField.getText().isBlank()) {
                hBoxCardBook.getChildren().clear();
            }
            controller.searchBookById(isbnField.getText()).apply(this::createCardBook, e -> {
                hBoxCardBook.getChildren().clear();
                new DialogError("No found", "Book cannot be found, check the ISBN");
            });
        });
        searchUserButton.setOnAction(event -> {
            if (userIdField.getText().isBlank()) {
                hBoxCardUser.getChildren().clear();
                controller.setLibraryMember(null);
            }
            controller.searchMemberById(userIdField.getText()).apply(this::createCardUser, e -> {
                hBoxCardUser.getChildren().clear();
                controller.setLibraryMember(null);
                new DialogError("No found", "User cannot be found, check the id");
            });
        });
        saveCheckoutButton.setOnAction(event -> {
            if (controller.getBook() == null) {
                new DialogError("Validation Fail", "Add a book");
            }
            if (controller.getLibraryMember() == null) {
                new DialogError("Validation Fail", "Add a library member");
            }
            controller.saveMemberCheckout().apply(s -> {
                openDialogSuccess();
            }, e -> {
                new DialogError("Error", "Something happen to save book");
            });
        });
    }

    void openDialogSuccess() {
        new DialogSuccess("Success", "Checkout record save successfull", this::cleanForm);
    }

    void cleanForm() {
        controller.setLibraryMember(null);
        controller.setBook(null);
        hBoxCardUser.getChildren().clear();
        hBoxCardBook.getChildren().clear();
        userIdField.setText("");
        isbnField.setText("");
    }

    void createCardUser(LibraryMember user) {
        controller.setLibraryMember(user);
        var buttonRemoveCard = new Button("Remove");
        buttonRemoveCard.setOnAction(event -> {
            hBoxCardUser.getChildren().clear();
            controller.setLibraryMember(null);
        });
        hBoxCardUser.getChildren().addAll(
                new MemberCard(user.getFirstName(), user.getLastName(), user.getTelephone(), user.getMemberId()),
                buttonRemoveCard
        );
    }


    void createCardBook(Book book) {
        controller.setBook(book);
        var buttonRemoveCard = new Button("Remove");
        buttonRemoveCard.setOnAction(event -> {
            hBoxCardBook.getChildren().clear();
            controller.setBook(null);
        });
        hBoxCardBook.getChildren().addAll(
                new BookCard(book),
                buttonRemoveCard
        );
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
