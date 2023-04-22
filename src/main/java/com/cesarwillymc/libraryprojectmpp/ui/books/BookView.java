package com.cesarwillymc.libraryprojectmpp.ui.books;

import com.almasb.fxgl.core.View;
import com.cesarwillymc.libraryprojectmpp.ui.books.view.BookCard;
import com.cesarwillymc.libraryprojectmpp.ui.members.view.MemberCard;
import com.cesarwillymc.libraryprojectmpp.ui.view.ButtonCard;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.function.Consumer;

public class BookView implements View {
    private final Node node;

    public BookView(Runnable navigateAddBook, Consumer<String> navigateBookDetail) {
        // Create a BorderPane to hold all the components
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Create a VBox to hold the search components
        VBox searchBox = new VBox();
        searchBox.setSpacing(10);
        root.setTop(searchBox);

        // Create the search TextField and Button
        TextField searchField = new TextField();
        searchField.setPromptText("Search by Id");

        Button searchButton = new Button("Search");
        Button addNewMemberButton = new Button("Add New Member");
        addNewMemberButton.setOnAction(event -> navigateAddBook.run());
        HBox searchControls = new HBox(searchField, searchButton, addNewMemberButton);
        searchControls.setAlignment(Pos.CENTER_LEFT);
        searchControls.setSpacing(10);
        searchBox.getChildren().add(searchControls);

        // Create the search filters
        HBox filterBox = new HBox();
        filterBox.setSpacing(10);
        searchBox.getChildren().add(filterBox);

        ButtonCard filter1 = new ButtonCard("Name");
        ButtonCard filter2 = new ButtonCard("Id");
        ButtonCard filter3 = new ButtonCard("Telephone");
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
        ListView<BookCard> cardList = new ListView<>(FXCollections.observableArrayList(new BookCard("hola", "cesar", LocalDate.now(), LocalDate.now(),10),new BookCard("hola", "cesar", LocalDate.now(), LocalDate.now(),10)));
        cardList.setPrefSize(400, 400);
        cardList.setPadding(new Insets(20));
        cardList.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() >= 0) {
                navigateBookDetail.accept(cardList.getItems().get(newValue.intValue()).getIsbn());
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
