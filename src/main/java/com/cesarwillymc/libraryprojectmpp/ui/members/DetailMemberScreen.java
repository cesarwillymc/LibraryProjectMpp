package com.cesarwillymc.libraryprojectmpp.ui.members;

import com.cesarwillymc.libraryprojectmpp.domain.enums.TypeStatusReturnBook;
import com.cesarwillymc.libraryprojectmpp.ui.di.DIControllers;
import com.cesarwillymc.libraryprojectmpp.ui.members.controller.DetailMemberController;
import com.cesarwillymc.libraryprojectmpp.ui.members.entity.LibraryMemberUI;
import com.cesarwillymc.libraryprojectmpp.ui.members.entity.ParamsTable;
import com.cesarwillymc.libraryprojectmpp.ui.view.DialogError;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.time.LocalDate;

public class DetailMemberScreen extends Stage {
    public static final DetailMemberScreen INSTANCE = new DetailMemberScreen();

    private final Label firstNameLabel = new Label("First Name:");
    private final Label lastNameLabel = new Label("Last Name:");
    private final Label telephoneLabel = new Label("Telephone:");
    private final Label addressLabel = new Label("Address:");

    private final Label firstNameValue = new Label();
    private final Label lastNameValue = new Label();
    private final Label telephoneValue = new Label();
    private final Label addressValue = new Label();
    private TableView<LibraryMemberUI> tableView;
    Stage previusStage;

    final DetailMemberController controller = DIControllers.createDetailMemberController();
    String id;

    DetailMemberScreen() {

    }

    public void setStage(Stage previusStage, String id) {
        this.id = id;
        setTitle("Detail member " + id);
        this.previusStage = previusStage;
        VBox box = new VBox();
        tableView = createTableView();
        controller.getUserById(id).apply(s -> {
            firstNameValue.setText(s.getFirstName());
            lastNameValue.setText(s.getLastName());
            telephoneValue.setText(s.getTelephone());
            addressValue.setText(s.getAddress().toString());
        }, e -> new DialogError("Error", "Loading user " + id));
        Button backButton = new Button("Return");
        backButton.setPadding(new Insets(10));
        backButton.setOnAction(event -> {
            tableView = new TableView<>();
            close();
            previusStage.show();

        });
        Image defaultPhoto = new Image("https://via.placeholder.com/150");

        // Set up labels
        HBox firstNameBox = new HBox(5, firstNameLabel, firstNameValue);
        firstNameBox.setPadding(new Insets(10));
        HBox lastNameBox = new HBox(5, lastNameLabel, lastNameValue);
        lastNameBox.setPadding(new Insets(10));
        HBox telephoneBox = new HBox(5, telephoneLabel, telephoneValue);
        telephoneBox.setPadding(new Insets(10));
        HBox addressBox = new HBox(5, addressLabel, addressValue);
        addressBox.setPadding(new Insets(10));
        box.getChildren().addAll(backButton, new ImageView(defaultPhoto), firstNameBox, lastNameBox, telephoneBox, addressBox);


        box.getChildren().add(tableView);
        Scene scene = new Scene(box, 800, 600);
        setScene(scene);
        show();

        onLoadTable();

    }

    private void onLoadTable() {
        controller.getRecordMembers(id).apply(s -> {

            var listMapper = s.stream().map(x -> new LibraryMemberUI(
                    x.getBook().getCopyNum(),
                    x.getBook().getName(),
                    x.getBook().getBookISBN(),
                    x.getDateBorrow(),
                    x.getDateDue(),
                    x.getDateReturned(),
                    LibraryMemberUI.getColor(x.getStatusReturnBook()),
                    x.getStatusReturnBook(),
                    x.getId()
            )).toList();
            setMemberRecords(FXCollections.observableList(listMapper));
        }, e -> {

        });
    }

    @SuppressWarnings("unchecked")
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

        changeState.setCellFactory(column -> new TableCell<>() {
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
        statusCol.setCellFactory(column -> new TableCell<>() {
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
        table.getColumns().addAll(idCopyBook,titleCol, isbnCol, dateBorrowCol, dateDueCol, dateReturnedCol, statusCol, typeStatusCol,changeState);

        return table;
    }

    void changeStateDelivered(ParamsTable paramsTable) {
        controller.updateMemberRecord(paramsTable.getIdCheckOut()).apply(x -> {
            onLoadTable();
        }, e -> {
            new DialogError("Error", e.getMessage());
        });
    }
    public void setAddress(String address) {
        addressValue.setText(address);
    }

    public void setMemberRecords(ObservableList<LibraryMemberUI> memberRecords) {
        tableView.setItems(memberRecords);
    }


}
