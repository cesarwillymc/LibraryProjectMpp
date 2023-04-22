package com.cesarwillymc.libraryprojectmpp.ui.home.view;

import com.almasb.fxgl.core.View;
import com.cesarwillymc.libraryprojectmpp.ui.books.BookView;
import com.cesarwillymc.libraryprojectmpp.ui.home.DashboardView;
import com.cesarwillymc.libraryprojectmpp.ui.members.MemberView;
import com.cesarwillymc.libraryprojectmpp.ui.profile.ProfileView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private DashboardView dashboardView;
    private BookView bookView;
    private MemberView memberView;
    private ProfileView profileView;
    private BorderPane root;
    private GridPane menuItems;
    private Button dashboardButton;
    private Button bookButton;
    private Button memberButton;
    private Button profileButton;

    public Menu(DashboardView dashboardView, BookView bookView, MemberView memberView, ProfileView profileView) {
        this.dashboardView = dashboardView;
        this.bookView = bookView;
        this.memberView = memberView;
        this.profileView = profileView;
        createMenu();
    }

    private void createMenu() {

        menuItems = new GridPane();
        menuItems.setAlignment(Pos.BASELINE_CENTER);
        dashboardButton = new Button("Dashboard");
        bookButton = new Button("Book");
        memberButton = new Button("Member");
        profileButton = new Button("Profile");

        // Add button handlers to switch between views
        dashboardButton.setOnAction(event -> switchToDashboard());
        bookButton.setOnAction(event -> switchToBook());
        memberButton.setOnAction(event -> switchToMember());
        profileButton.setOnAction(event -> switchToProfile());

        menuItems.setPadding(new Insets(20,20,20,20));
        menuItems.setHgap(20);
        menuItems.add(dashboardButton,0,0);
        menuItems.add(bookButton,1,0);
        menuItems.add(memberButton,2,0);
        menuItems.add(profileButton,3,0);

        //menuItems.getChildren().addAll(dashboardButton, bookButton, memberButton, profileButton);

        // Set the default view to the dashboard
        root = new BorderPane(dashboardView.getNode(), null, null, menuItems, null);
        root.getBottom().minWidth(root.getMaxWidth());
        root.getBottom();


    }

    public void switchToDashboard() {
        root.setCenter(dashboardView.getNode());
        setActiveButton(dashboardButton);
    }

    public void switchToBook() {
        root.setCenter(bookView.getNode());
        setActiveButton(bookButton);
    }

    public void switchToMember() {
        root.setCenter(memberView.getNode());
        setActiveButton(memberButton);
    }

    public void switchToProfile() {
        root.setCenter(profileView.getNode());
        setActiveButton(profileButton);
    }

    private void setActiveButton(Button button) {
        dashboardButton.getStyleClass().remove("active");
        bookButton.getStyleClass().remove("active");
        memberButton.getStyleClass().remove("active");
        profileButton.getStyleClass().remove("active");
        button.getStyleClass().add("active");
    }

    public Node getNode() {
        return root;
    }
}