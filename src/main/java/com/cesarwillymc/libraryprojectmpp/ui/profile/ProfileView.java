package com.cesarwillymc.libraryprojectmpp.ui.profile;

import com.almasb.fxgl.core.View;
import com.cesarwillymc.libraryprojectmpp.domain.entities.User;
import com.cesarwillymc.libraryprojectmpp.ui.di.DIControllers;
import com.cesarwillymc.libraryprojectmpp.ui.profile.controller.ProfileController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class ProfileView implements View {
    private final Node node;
    BorderPane root;
    ProfileController profileController = DIControllers.createProfileController();

    public ProfileView(Runnable navigate) {
        // Create chart nodes and add them to a parent node
        // Set up UI

        root = new BorderPane();
        setUpHeader();

        profileController.getInformation().apply(this::setUpProfileInfo, this::showErrorMessage);


        setUpLogoutButton(navigate);

        node = root;
    }

    public void showErrorMessage(Exception e) {

        Image image = new Image("https://img.freepik.com/free-vector/oops-404-error-with-broken-robot-concept-illustration_114360-1920.jpg?w=2000", 100, 100, true, false);
        ImageView imageView = new ImageView(image);
        Label label = new Label(e.getMessage(), imageView);
        label.setContentDisplay(ContentDisplay.LEFT);
        label.setGraphicTextGap(20);
        label.setStyle("-fx-text-fill: red;");

        // Add the label to the scene
        // For example, if this method is inside a Controller class:
        VBox vBox = new VBox(imageView, label);
        vBox.setPadding(new Insets(20));
        root.setCenter(vBox);
    }

    private void setUpHeader() {
        // Set up header with title and back button (if applicable)
        HBox header = new HBox();
        header.setPadding(new Insets(10));
        header.setAlignment(Pos.CENTER_LEFT);

        Text title = new Text("Profile");


        header.getChildren().add(title);

        root.setTop(header);
    }

    private void setUpProfileInfo(User user) {
        System.out.println("userrr" +user);
        // Set up profile information section
        VBox profileInfo = new VBox();
        profileInfo.setAlignment(Pos.CENTER);
        profileInfo.setSpacing(20);
        profileInfo.setPadding(new Insets(50));

        // Create circular profile image
        ImageView profileImage = new ImageView(new Image(user.getImageUrl(),190,190,true,false));
        Circle clip = new Circle(75, 75, 75);
        profileImage.setClip(clip);
        StackPane imageContainer = new StackPane(profileImage);
        imageContainer.setPrefSize(150, 150);

        // Create name and telephone labels
        Text name = new Text(user.getFirstName() + " " + user.getLastName());
        Text telephoneLabel = new Text("Telephone: " + user.getLastName());

        profileInfo.getChildren().addAll(imageContainer, name, telephoneLabel);

        root.setCenter(profileInfo);
    }

    private void setUpLogoutButton(Runnable navigate) {
        // Set up logout button at bottom of screen
        Button logoutButton = new Button("Logout");
        logoutButton.getStyleClass().add("logout-button");
        logoutButton.setOnAction(e -> {
            // Handle logout button click
            profileController.signOut();
            navigate.run();
        });

        HBox bottom = new HBox(logoutButton);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(20));

        root.setBottom(bottom);
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
