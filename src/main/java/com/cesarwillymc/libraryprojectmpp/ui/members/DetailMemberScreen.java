package com.cesarwillymc.libraryprojectmpp.ui.members;

import com.cesarwillymc.libraryprojectmpp.domain.entities.MemberRecord;
import com.cesarwillymc.libraryprojectmpp.ui.di.DIControllers;
import com.cesarwillymc.libraryprojectmpp.ui.members.controller.DetailMemberController;
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
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;

public class DetailMemberScreen extends Stage {
    public static DetailMemberScreen INSTANCE = new DetailMemberScreen();

    private final Label firstNameLabel = new Label("First Name:");
    private final Label lastNameLabel = new Label("Last Name:");
    private final Label telephoneLabel = new Label("Telephone:");
    private final Label addressLabel = new Label("Address:");

    private final Label firstNameValue = new Label();
    private final Label lastNameValue = new Label();
    private final Label telephoneValue = new Label();
    private final Label addressValue = new Label();
    private VBox box;
    private TableView<MemberRecord> tableView = new TableView<>();
    Stage previusStage;

    DetailMemberController controller = DIControllers.createDetailMemberController();

    DetailMemberScreen() {

    }

    public void setStage(Stage previusStage, String id) {
        setTitle("Detail member "+id);
        this.previusStage = previusStage;
        box = new VBox();

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

        // Set up table columns
        TableColumn<MemberRecord, String> idColumn = new TableColumn<>("ID");
        TableColumn<MemberRecord, String> bookColumn = new TableColumn<>("Book");
        TableColumn<MemberRecord, LocalDate> borrowColumn = new TableColumn<>("Date Borrowed");
        TableColumn<MemberRecord, LocalDate> dueColumn = new TableColumn<>("Due Date");
        TableColumn<MemberRecord, LocalDate> returnedColumn = new TableColumn<>("Date Returned");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("Book"));
        borrowColumn.setCellValueFactory(new PropertyValueFactory<>("dateBorrow"));
        dueColumn.setCellValueFactory(new PropertyValueFactory<>("dateDue"));
        returnedColumn.setCellValueFactory(new PropertyValueFactory<>("dateReturned"));

        // Highlight "date returned" column based on current date
        LocalDate currentDate = LocalDate.now();
        returnedColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<MemberRecord, LocalDate> call(TableColumn<MemberRecord, LocalDate> column) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        if (empty || date == null) {
                            setText("");
                            setStyle("");
                            return;
                        }
                        setText(date.toString());
                        if (date.isBefore(currentDate)) {
                            setTextFill(Color.RED);
                        } else {
                            setTextFill(Color.GREEN);
                        }
                    }
                };
            }
        });

        tableView.getColumns().addAll(idColumn, bookColumn, borrowColumn, dueColumn, returnedColumn);
        box.getChildren().add(tableView);
        Scene scene = new Scene(box, 800, 600);
        setScene(scene);
        show();

        controller.getRecordMembers(id).apply(s -> {
            setMemberRecords(FXCollections.observableList(s));
        }, e -> {

        });

    }

    public void setFirstName(String firstName) {
        firstNameValue.setText(firstName);
    }

    public void setLastName(String lastName) {
        lastNameValue.setText(lastName);
    }

    public void setTelephone(String telephone) {
        telephoneValue.setText(telephone);
    }

    public void setAddress(String address) {
        addressValue.setText(address);
    }

    public void setMemberRecords(ObservableList<MemberRecord> memberRecords) {
        tableView.setItems(memberRecords);
    }


}
