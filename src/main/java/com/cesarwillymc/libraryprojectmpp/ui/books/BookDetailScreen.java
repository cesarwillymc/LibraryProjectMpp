package com.cesarwillymc.libraryprojectmpp.ui.books;

import com.cesarwillymc.libraryprojectmpp.domain.entities.Author;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;
import com.cesarwillymc.libraryprojectmpp.domain.entities.MemberRecord;
import com.cesarwillymc.libraryprojectmpp.domain.enums.TypeStatusReturnBook;
import com.cesarwillymc.libraryprojectmpp.ui.books.controller.BookDetailController;
import com.cesarwillymc.libraryprojectmpp.ui.di.DIControllers;
import com.cesarwillymc.libraryprojectmpp.ui.members.entity.LibraryMemberUI;
import com.cesarwillymc.libraryprojectmpp.ui.members.entity.ParamsTable;
import com.cesarwillymc.libraryprojectmpp.ui.view.DialogError;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class BookDetailScreen extends Stage {
    public static BookDetailScreen INSTANCE = new BookDetailScreen();

    private ObservableList<MemberRecord> tableData;
    private TextField copyToAddField;

    Button backButton;
    BookDetailController controller = DIControllers.createDetailBookController();
    Label numOfCopiesLabel;
    Book book;
    private TableView<LibraryMemberUI> tableView;

    public BookDetailScreen() {

    }

    public void setStage(Stage previusStage, Book book) {
        this.book = book;
        setTitle("Detail of " + book.getTitle());
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


        // Add columns to table
        tableView = createTableView();
        VBox root = new VBox();
        // Add components to screen
        VBox topBox = new VBox(10, isbnLabel, titleLabel, authorsLabel, numOfCopiesLabel, addCopyButton);

        topBox.setPadding(new Insets(10));

        root.getChildren().addAll(backButton, topBox, tableView);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 800, 600);
        setScene(scene);
        show();

        onLoadTable();
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
                    controller.updateNumOfCopies(book).apply(s -> {
                        numOfCopiesLabel.setText("Number of Copies: " + book.getNumCopies());
                    }, e -> {
                        new DialogError("Error", "Add num of copies failed");
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

    private void onLoadTable() {
        controller.getMemberRecordsById(book.getIsbn()).apply(s -> {
            System.out.println(s);
            var listMapper = s.stream().map(x -> {
                var typeBook = x.getStatusReturnBook();
                return new LibraryMemberUI(
                        x.getBook().getCopyNum(),
                        x.getBook().getName(),
                        x.getBook().getBookISBN(),
                        x.getDateBorrow(),
                        x.getDateDue(),
                        x.getDateReturned(),
                        LibraryMemberUI.getColor(typeBook),
                        typeBook,
                        x.getId()
                );
            }).toList();
            setMemberRecords(FXCollections.observableList(listMapper));
        }, e -> {

        });
    }

    TableView<LibraryMemberUI> createTableView() {
        TableView<LibraryMemberUI> table = new TableView<>();

        TableColumn<LibraryMemberUI, Integer> idCopyBook = new TableColumn<>("Id book");
        idCopyBook.setCellValueFactory(new PropertyValueFactory<>("idCopyBook"));
        TableColumn<LibraryMemberUI, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<LibraryMemberUI, String> isbnCol = new TableColumn<>("ISBN");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        TableColumn<LibraryMemberUI, LocalDate> dateBorrowCol = new TableColumn<>("Borrow Date");
        dateBorrowCol.setCellValueFactory(new PropertyValueFactory<>("dateBorrow"));

        TableColumn<LibraryMemberUI, LocalDate> dateDueCol = new TableColumn<>("Due Date");
        dateDueCol.setCellValueFactory(new PropertyValueFactory<>("dateDue"));

        TableColumn<LibraryMemberUI, LocalDate> dateReturnedCol = new TableColumn<>("Returned Date");
        dateReturnedCol.setCellValueFactory(new PropertyValueFactory<>("dateReturned"));

        TableColumn<LibraryMemberUI, Color> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<LibraryMemberUI, TypeStatusReturnBook> typeStatusCol = new TableColumn<>("Return Type");
        typeStatusCol.setCellValueFactory(new PropertyValueFactory<>("typeStatus"));
        TableColumn<LibraryMemberUI, ParamsTable> changeState = new TableColumn<>("Change state");
        changeState.setCellValueFactory(new PropertyValueFactory<>("paramsTable"));

        changeState.setCellFactory(column -> new TableCell<LibraryMemberUI, ParamsTable>() {
            @Override
            protected void updateItem(ParamsTable item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (item.getTypeStatusReturnBook() != TypeStatusReturnBook.FINISH) {
                        Button button = new Button("Delivered");
                        button.setOnAction(event -> {
                            changeStateDelivered(item);
                        });
                        setGraphic(button);
                    } else {
                        setText(null);
                        setGraphic(null);
                    }

                }
            }
        });
        statusCol.setCellFactory(column -> new TableCell<LibraryMemberUI, Color>() {
            @Override
            protected void updateItem(Color item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Rectangle colorBox = new Rectangle(20, 20, item);
                    setGraphic(colorBox);
                }
            }
        });
        table.getColumns().addAll(idCopyBook,titleCol, isbnCol, dateBorrowCol, dateDueCol, dateReturnedCol, statusCol, typeStatusCol, changeState);

        return table;
    }

    void changeStateDelivered(ParamsTable paramsTable) {
        controller.updateMemberRecord(paramsTable.getIdCheckOut()).apply(x -> {
            onLoadTable();
        }, e -> {
            new DialogError("Error", e.getMessage());
        });
    }

    public void setMemberRecords(ObservableList<LibraryMemberUI> memberRecords) {
        tableView.setItems(memberRecords);
    }
}