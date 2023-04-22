package com.cesarwillymc.libraryprojectmpp.ui.members;

import com.cesarwillymc.libraryprojectmpp.domain.entities.MemberRecord;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import java.time.LocalDate;

public class DetailMemberScreen extends VBox {

    private final Label firstNameLabel = new Label("First Name:");
    private final Label lastNameLabel = new Label("Last Name:");
    private final Label telephoneLabel = new Label("Telephone:");
    private final Label addressLabel = new Label("Address:");

    private final Label firstNameValue = new Label();
    private final Label lastNameValue = new Label();
    private final Label telephoneValue = new Label();
    private final Label addressValue = new Label();

    private final TableView<MemberRecord> tableView = new TableView<>();

    public DetailMemberScreen() {
        Image defaultPhoto = new Image("https://via.placeholder.com/150");

        // Set up labels
        HBox firstNameBox = new HBox(5, firstNameLabel, firstNameValue);
        HBox lastNameBox = new HBox(5, lastNameLabel, lastNameValue);
        HBox telephoneBox = new HBox(5, telephoneLabel, telephoneValue);
        HBox addressBox = new HBox(5, addressLabel, addressValue);
        getChildren().addAll(new ImageView(defaultPhoto),firstNameBox, lastNameBox, telephoneBox, addressBox);

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
        getChildren().add(tableView);
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
