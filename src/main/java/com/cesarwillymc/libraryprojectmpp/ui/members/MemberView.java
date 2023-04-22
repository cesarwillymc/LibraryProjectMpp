package com.cesarwillymc.libraryprojectmpp.ui.members;

import com.almasb.fxgl.core.View;
import com.cesarwillymc.libraryprojectmpp.ui.di.DIControllers;
import com.cesarwillymc.libraryprojectmpp.ui.members.controller.MemberController;
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
    private final MemberController memberController = DIControllers.createMemberController();

    public MemberView(Runnable navigateAddMember, Consumer<String> navigateDetailMember) {
        ListView<MemberCard> cardList = new ListView<>(FXCollections.observableArrayList());

        memberController.getInitialListMembers((x) -> {
            cardList.setItems(FXCollections.observableArrayList(x));
            cardList.refresh();
        });
        // Create a BorderPane to hold all the components
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Create a VBox to hold the search components
        VBox searchBox = new VBox();
        searchBox.setSpacing(10);
        root.setTop(searchBox);

        // Create the search TextField and Button
        TextField searchField = new TextField();
        searchField.setPromptText("Search by id");

        Button searchButton = new Button("Search");
        Button reload = new Button("Reload List");
        reload.setOnAction(event -> memberController.getInitialListMembers((x) -> {
            cardList.setItems(FXCollections.observableArrayList(x));
            cardList.refresh();
        }));
        Button addNewMemberButton = new Button("Add New Member Library");
        searchButton.setOnAction(event -> memberController.searchInList(searchField.getText(), (x) -> {
            cardList.setItems(FXCollections.observableArrayList(x));
            cardList.refresh();
        }));
        addNewMemberButton.setOnAction(event -> navigateAddMember.run());
        HBox searchControls = new HBox(searchField, searchButton, reload, addNewMemberButton);
        searchControls.setAlignment(Pos.CENTER_LEFT);
        searchControls.setSpacing(10);
        searchBox.getChildren().add(searchControls);

        // Create the search filters
        HBox filterBox = new HBox();
        filterBox.setSpacing(10);
        searchBox.getChildren().add(filterBox);

        ButtonCard filter1 = new ButtonCard("Name", (b) -> memberController.filterByName(b, (x) -> {
            cardList.setItems(FXCollections.observableArrayList(x));
            cardList.refresh();
        }));
        ButtonCard filter2 = new ButtonCard("Id", (b) -> memberController.filterById(b, (x) -> {
            cardList.setItems(FXCollections.observableArrayList(x));
            cardList.refresh();
        }));
        ButtonCard filter3 = new ButtonCard("Telephone", (b) -> memberController.filterByTelephone(b, (x) -> {
            cardList.setItems(FXCollections.observableArrayList(x));
            cardList.refresh();
        }));
        Button clearFilter = new Button("Clear filters");

        clearFilter.setOnAction(event -> memberController.cleanFilter((x) -> {
            cardList.setItems(FXCollections.observableArrayList(x));
            cardList.refresh();
        }));

        filterBox.getChildren().addAll(new Label("Filters: "), filter1, filter2, filter3, clearFilter);
        filterBox.setPadding(new Insets(10));
        // Create the ListView of cards
        cardList.setPrefSize(400, 400);
        cardList.setPadding(new Insets(20));
        cardList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // double-click
                MemberCard selectedItem = cardList.getSelectionModel().getSelectedItem();
                navigateDetailMember.accept(selectedItem.getMemberId());
                cardList.getSelectionModel().clearSelection();
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
