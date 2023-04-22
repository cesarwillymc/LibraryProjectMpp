package com.cesarwillymc.libraryprojectmpp.ui.books;


import com.cesarwillymc.libraryprojectmpp.domain.entities.Author;
import com.cesarwillymc.libraryprojectmpp.ui.books.controller.AddBookController;
import com.cesarwillymc.libraryprojectmpp.ui.di.DIControllers;
import com.cesarwillymc.libraryprojectmpp.ui.view.DialogError;
import com.cesarwillymc.libraryprojectmpp.ui.view.DialogSuccess;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AddBookScreen extends Stage {
    public static AddBookScreen INSTANCE = new AddBookScreen();

    private TextField isbnField;
    private TextField titleField;
    private List<Author> authors;
    private TextField numOfCopiesField;
    Stage previusStage;
    ListView<Author> listView = new ListView<>();

    AddBookController controller= DIControllers.createAddBookController();
    public void setStage(Stage stage) {
        setTitle("Add Author");
        listView.setPrefHeight(100);
        previusStage = stage;
        isbnField = new TextField();
        titleField = new TextField();
        authors = new ArrayList<>();
        numOfCopiesField = new TextField();
        numOfCopiesField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                numOfCopiesField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        Label isbnLabel = new Label("ISBN:");
        Label titleLabel = new Label("Title:");
        Label authorLabel = new Label("Author(s):");
        Label numOfCopiesLabel = new Label("Number of Copies:");

        Button addAuthorButton = new Button("Add Author");
        addAuthorButton.setOnAction(event -> addAuthor());

        Button saveButton = new Button("Save");
        Button backButton = new Button("Back previous screen");
        backButton.setPadding(new Insets(15));
        saveButton.setPadding(new Insets(15));
        saveButton.setOnAction(event -> {
           if( isValidateForm())
            saveBook();
        });

        backButton.setOnAction(event -> {
            close();
            stage.show();
        });

        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(10));
        formGrid.add(isbnLabel, 0, 0);
        formGrid.add(isbnField, 1, 0);
        formGrid.add(titleLabel, 0, 1);
        formGrid.add(titleField, 1, 1);
        formGrid.add(authorLabel, 0, 2);
        formGrid.add(listView, 1, 2);
        formGrid.add(addAuthorButton, 1, 3);
        formGrid.add(numOfCopiesLabel, 0, 4);
        formGrid.add(numOfCopiesField, 1, 4);

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(saveButton,backButton);

        VBox root = new VBox();
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(formGrid, buttonBox);

        Scene scene = new Scene(root, 800, 600);
        setScene(scene);
        show();
    }

    private boolean isValidateForm() {
        if (titleField.getText().isBlank()) {
            new DialogError("Error validation", "Title cannot be empty");
            return false;
        }
        if (isbnField.getText().isBlank()) {
            new DialogError("Error validation", "ISBN cannot be empty");
            return false;
        }
        if (numOfCopiesField.getText().isBlank()) {
            new DialogError("Error validation", "Num of Copies cannot be empty");
            return false;
        }
        return true;
    }

    private void setAuthorList(List<Author> authors) {


        listView.setItems(FXCollections.observableList(authors));

    }

    private void addAuthor() {

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        // Add the form elements to the grid pane
        Label firstNameLabel = new Label("First Name:");
        gridPane.add(firstNameLabel, 0, 0);
        TextField firstNameTextField = new TextField();
        gridPane.add(firstNameTextField, 1, 0);

        Label lastNameLabel = new Label("Last Name:");
        gridPane.add(lastNameLabel, 0, 1);
        TextField lastNameTextField = new TextField();
        gridPane.add(lastNameTextField, 1, 1);

        // Create the dialog and set the content to be the grid pane
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Enter Name");
        dialog.getDialogPane().setContent(gridPane);

        // Create a save button and add it to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        dialog.getDialogPane().lookupButton(ButtonType.APPLY).setDisable(true);
        dialog.getDialogPane().lookupButton(ButtonType.APPLY).disableProperty().bind(
                firstNameTextField.textProperty().isEmpty().or(lastNameTextField.textProperty().isEmpty())
        );

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.APPLY) {
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                Author author = new Author(firstName, lastName, "", null, "");
                authors.add(author);
                setAuthorList(authors);
                return firstName + " " + lastName;
            }
            return null;
        });

        dialog.getDialogPane().setPrefWidth(300);

        // Show the dialog and wait for the user to close it
        dialog.showAndWait();

    }

    private void saveBook() {
        String isbn = isbnField.getText();
        String title = titleField.getText();
        Integer numOfCopies = Integer.parseInt(numOfCopiesField.getText());
        // save the book data
        controller.saveBook(isbn,numOfCopies,title,authors).apply(x -> new DialogSuccess("Success", "The new book was created", () -> {
            close();
            previusStage.show();
        }), e -> new DialogError("Error saving member", e.getMessage()));

    }

}
