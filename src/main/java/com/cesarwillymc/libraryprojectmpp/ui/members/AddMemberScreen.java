package com.cesarwillymc.libraryprojectmpp.ui.members;

import com.cesarwillymc.libraryprojectmpp.ui.HomeScreen;
import com.cesarwillymc.libraryprojectmpp.ui.di.DIControllers;
import com.cesarwillymc.libraryprojectmpp.ui.members.controller.AddMemberController;
import com.cesarwillymc.libraryprojectmpp.ui.view.DialogError;
import com.cesarwillymc.libraryprojectmpp.ui.view.DialogSuccess;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddMemberScreen extends Stage {
    public static final AddMemberScreen INSTANCE = new AddMemberScreen();
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField telephoneField;
    private TextField addressField;
    private TextField cityField;
    private TextField stateField;
    private TextField zipCodeField;
    private TextField documentField;
    HomeScreen previousStage;

    final AddMemberController controller = DIControllers.createAddMemberController();

    AddMemberScreen() {

    }

    public void setStage(HomeScreen previusStage) {
        setTitle("Add Member");
        this.previousStage = previusStage;
        // Create labels
        Label documentLabel = new Label("Number document:");
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label telephoneLabel = new Label("Telephone:");
        Label addressLabel = new Label("Address:");
        Label cityLabel = new Label("City:");
        Label zipCodeLabel = new Label("Zip code:");
        Label stateLabel = new Label("State:");

        // Create text fields
        documentField = new TextField();
        firstNameField = new TextField();
        lastNameField = new TextField();
        telephoneField = new TextField();
        addressField = new TextField();
        cityField = new TextField();
        zipCodeField = new TextField();
        stateField = new TextField();

        // Create the back button
        Button backButton = new Button("Return to previous screen");
        backButton.setOnAction(e -> {
            close();
            previusStage.show();
        });
        Button saveButton = new Button("Save Library");
        saveButton.setPadding(new Insets(10));
        saveButton.setOnAction(event -> {
            if (isValidationFields()) {
                controller.saveMember(
                        firstNameField.getText(),
                        lastNameField.getText(),
                        telephoneField.getText(),
                        documentField.getText(),
                        addressField.getText(),
                        cityField.getText(),
                        stateField.getText(),
                        zipCodeField.getText()
                ).apply(x -> {
                    new DialogSuccess("Success", "The new member was created", () -> {
                        close();
                        previusStage.show();
                    });
                }, e -> new DialogError("Error saving member", e.getMessage()));

            }

        });

        // Add labels, text fields, and buttons to the form layout
        var box = new VBox();
        box.setAlignment(Pos.BASELINE_CENTER);
        box.getChildren().addAll(
                documentLabel, documentField,
                firstNameLabel, firstNameField,
                lastNameLabel, lastNameField,
                telephoneLabel, telephoneField,
                addressLabel, addressField,
                cityLabel, cityField,
                stateLabel, stateField,
                zipCodeLabel, zipCodeField,
                saveButton,
                backButton
        );

        // Set spacing and padding
        box.setSpacing(10);
        box.setPadding(new Insets(10));

        Scene scene = new Scene(box, 800, 600);
        setScene(scene);
        show();
    }

    private boolean isValidationFields() {
        if (documentField.getText().isBlank()) {
            new DialogError("Error validation", "DocumentField cannot be empty");
            return false;
        }
        if (firstNameField.getText().isBlank()) {
            new DialogError("Error validation", "FirstName cannot be empty");
            return false;
        }
        if (telephoneField.getText().isBlank()) {
            new DialogError("Error validation", "Telephone cannot be empty");
            return false;
        }
        if (lastNameField.getText().isBlank()) {
            new DialogError("Error validation", "LastName cannot be empty");
            return false;
        }
        if (addressField.getText().isBlank()) {
            new DialogError("Error validation", "Address cannot be empty");
            return false;
        }
        if (cityField.getText().isBlank()) {
            new DialogError("Error validation", "City cannot be empty");
            return false;
        }
        if (stateField.getText().isBlank()) {
            new DialogError("Error validation", "State cannot be empty");
            return false;
        }
        if (zipCodeField.getText().isBlank()) {
            new DialogError("Error validation", "Zip code cannot be empty");
            return false;
        }


        return true;
    }
}
