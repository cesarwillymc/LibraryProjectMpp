package com.cesarwillymc.libraryprojectmpp.ui.books;

import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.LibraryMemberResponse;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Author;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;
import com.cesarwillymc.libraryprojectmpp.domain.entities.BookCopy;
import com.cesarwillymc.libraryprojectmpp.domain.entities.MemberRecord;
import com.cesarwillymc.libraryprojectmpp.ui.books.controller.BookDetailController;
import com.cesarwillymc.libraryprojectmpp.ui.di.DIControllers;
import com.cesarwillymc.libraryprojectmpp.ui.view.DialogError;
import com.cesarwillymc.libraryprojectmpp.ui.view.DialogSuccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BookDetailScreen extends Stage {
    public static BookDetailScreen INSTANCE = new BookDetailScreen();

    private TableView<MemberRecord> table;
    private ObservableList<MemberRecord> tableData;
    private TextField copyToAddField;

    Button backButton;
    BookDetailController controller = DIControllers.createDetailBookController();
    Label numOfCopiesLabel;
    Book book;

    public BookDetailScreen() {

    }
    public void setStage(Stage previusStage, Book book){
        this.book = book;
        setTitle("Detail of "+ book.getTitle());
        // Create labels to display book information
        Label isbnLabel = new Label("ISBN: " + book.getIsbn());
        Label titleLabel = new Label("Title: " + book.getTitle());
        Label authorsLabel = new Label("Authors: " + book.getAuthors().stream()
                .map(Author::toString)
                .collect(Collectors.joining(", ")));
         numOfCopiesLabel = new Label("Number of Copies: " + book.getNumCopies());

        // Create button to add more copies of book
        Button addCopyButton = new Button("Add More Copies");
        addCopyButton.setOnAction(event -> showAddCopyDialog());
        // Create the back button
        backButton = new Button("Return to previous screen");
        backButton.setOnAction(e -> {
            close();
            previusStage.show();
        });

        // Create table to display book records
        table = new TableView<>();
        tableData = FXCollections.observableArrayList();
        table.setItems(tableData);

        // Add columns to table
        TableColumn<MemberRecord, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<MemberRecord, LibraryMemberResponse> memberColumn = new TableColumn<>("Member");
        memberColumn.setCellValueFactory(new PropertyValueFactory<>("memberResponse"));

        TableColumn<MemberRecord, BookCopy> bookColumn = new TableColumn<>("Book Copy");
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("book"));

        TableColumn<MemberRecord, LocalDate> borrowDateColumn = new TableColumn<>("Date Borrowed");
        borrowDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateBorrow"));

        TableColumn<MemberRecord, LocalDate> dueDateColumn = new TableColumn<>("Due Date");
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateDue"));

        TableColumn<MemberRecord, LocalDate> returnDateColumn = new TableColumn<>("Return Date");
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateReturned"));

        table.getColumns().addAll(idColumn, memberColumn, bookColumn, borrowDateColumn, dueDateColumn, returnDateColumn);

        VBox root = new VBox();
        // Add components to screen
        VBox topBox = new VBox(10, isbnLabel, titleLabel, authorsLabel, numOfCopiesLabel, addCopyButton);

        topBox.setPadding(new Insets(10));

        root.getChildren().addAll(backButton,topBox,table);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 800, 600);
        setScene(scene);
        show();
    }

    private void showAddCopyDialog() {
        // Create dialog to add more copies of book
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Add Copies of Book");
        dialog.setHeaderText("Enter Number of Copies to Add");

        // Add controls to dialog
        copyToAddField = new TextField();
        dialog.getDialogPane().setContent(new VBox(10, new Label("Number of Copies:"), copyToAddField));

        // Add buttons to dialog
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        // Set result converter to parse text input into integer
        dialog.setResultConverter(buttonType -> {
            if (buttonType == addButton) {
                try {
                    int copiesToAdd = Integer.parseInt(copyToAddField.getText());
                    book.addCopies(copiesToAdd);
                    controller.updateNumOfCopies(book).apply(s->{
                        numOfCopiesLabel.setText("Number of Copies: " + book.getNumCopies());
                    },e ->{
                        new DialogError("Error","Add num of copies failed");
                    });
                    return copiesToAdd;
                } catch (NumberFormatException e) {

                    return null;
                }
            }
            return null;
        });

        // Show dialog and handle result
        dialog.show();
    }
}