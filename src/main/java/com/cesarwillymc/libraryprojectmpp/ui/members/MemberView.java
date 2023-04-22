package com.cesarwillymc.libraryprojectmpp.ui.members;

import com.almasb.fxgl.core.View;
import com.cesarwillymc.libraryprojectmpp.ui.view.ButtonCard;
import com.cesarwillymc.libraryprojectmpp.ui.members.view.MemberCard;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class MemberView implements View {
    private final Node node;

    public MemberView(Runnable navigateAddMember, Consumer<String> navigateDetailMember) {

        // Create a BorderPane to hold all the components
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Create a VBox to hold the search components
        VBox searchBox = new VBox();
        searchBox.setSpacing(10);
        root.setTop(searchBox);

        // Create the search TextField and Button
        TextField searchField = new TextField();
        searchField.setPromptText("Search by ISBN");

        Button searchButton = new Button("Search");
        Button addNewMemberButton = new Button("Add New Book");
        addNewMemberButton.setOnAction(event -> navigateAddMember.run());
        HBox searchControls = new HBox(searchField, searchButton, addNewMemberButton);
        searchControls.setAlignment(Pos.CENTER_LEFT);
        searchControls.setSpacing(10);
        searchBox.getChildren().add(searchControls);

        // Create the search filters
        HBox filterBox = new HBox();
        filterBox.setSpacing(10);
        searchBox.getChildren().add(filterBox);

        ButtonCard filter1 = new ButtonCard("Title");
        ButtonCard filter2 = new ButtonCard("Date created");
        ButtonCard filter3 = new ButtonCard("Date updated");
        ButtonCard clearFilter = new ButtonCard("Clear filters");
        clearFilter.setOnAction(event -> {

        });
        filter3.setOnAction(event -> {

        });
        filter3.setOnAction(event -> {

        });
        filter3.setOnAction(event -> {

        });
        filterBox.getChildren().addAll(new Label("Filters: "), filter1, filter2, filter3, clearFilter);
        filterBox.setPadding(new Insets(10));
        // Create the ListView of cards
        ListView<MemberCard> cardList = new ListView<>(FXCollections.observableArrayList(new MemberCard("hola", "cesar", "123123123", "213123"), new MemberCard("hola", "cesar", "123123123", "213123")));
        cardList.setPrefSize(400, 400);
        cardList.setPadding(new Insets(20));
        cardList.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() >= 0) {
                navigateDetailMember.accept(cardList.getItems().get(newValue.intValue()).getMemberId());
            }
        });

        root.setCenter(cardList);

        this.node = root;
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
