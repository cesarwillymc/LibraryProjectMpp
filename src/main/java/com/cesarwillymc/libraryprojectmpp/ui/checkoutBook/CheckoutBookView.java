package com.cesarwillymc.libraryprojectmpp.ui.checkoutBook;

import com.almasb.fxgl.core.View;
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
    private TextField isbnField;
    private TextField userIdField;

    public CheckoutBookView() {
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

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

        root.getChildren().addAll(isbnBox, userIdBox);

        node = root;
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
