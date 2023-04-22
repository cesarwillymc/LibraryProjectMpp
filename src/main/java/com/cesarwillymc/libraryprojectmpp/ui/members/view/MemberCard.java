package com.cesarwillymc.libraryprojectmpp.ui.members.view;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class MemberCard extends StackPane {

    private final String memberId;

    public MemberCard(String firstName, String lastName, String telephone, String memberId) {
        this.memberId = memberId;

        // Create labels for member information
        Label nameLabel = new Label(firstName + " " + lastName);
        Label telephoneLabel = new Label("Telephone: " + telephone);
        Label memberIdLabel = new Label("Member ID: " + memberId);

        // Create grid to hold labels
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(10));

        // Add labels to grid
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(telephoneLabel, 0, 1);
        gridPane.add(memberIdLabel, 0, 2);

        // Add grid to card
        getChildren().add(gridPane);
    }

    public String getMemberId() {
        return memberId;
    }
}
