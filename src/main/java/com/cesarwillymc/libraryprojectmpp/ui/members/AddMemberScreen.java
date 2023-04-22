package com.cesarwillymc.libraryprojectmpp.ui.members;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddMemberScreen extends VBox {
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField telephoneField;
    private TextField addressField;
    private Button backButton;

    public AddMemberScreen() {
        // Create labels
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label telephoneLabel = new Label("Telephone:");
        Label addressLabel = new Label("Address:");

        // Create text fields
        firstNameField = new TextField();
        lastNameField = new TextField();
        telephoneField = new TextField();
        addressField = new TextField();

        // Add validation listeners to text fields
        firstNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\p{L}+")) {
                firstNameField.setText(oldValue);
            }
        });
        lastNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\p{L}+")) {
                lastNameField.setText(oldValue);
            }
        });
        telephoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{10}")) {
                telephoneField.setText(oldValue);
            }
        });
        addressField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 50) {
                addressField.setText(oldValue);
            }
        });

        // Create the back button
        backButton = new Button("Return to previous screen");
        backButton.setOnAction(e -> {
            // Code to switch back to previous screen
        });

        // Add labels, text fields, and buttons to the form layout
        getChildren().addAll(
                firstNameLabel, firstNameField,
                lastNameLabel, lastNameField,
                telephoneLabel, telephoneField,
                addressLabel, addressField,
                backButton
        );

        // Set spacing and padding
        setSpacing(10);
        setPadding(new Insets(10));
    }
}
