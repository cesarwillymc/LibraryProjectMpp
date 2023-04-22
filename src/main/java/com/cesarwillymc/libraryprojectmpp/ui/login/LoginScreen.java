package com.cesarwillymc.libraryprojectmpp.ui.login;

import com.cesarwillymc.libraryprojectmpp.ui.HomeScreen;
import com.cesarwillymc.libraryprojectmpp.ui.login.controller.LoginController;
import com.cesarwillymc.libraryprojectmpp.ui.di.DIControllers;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginScreen extends Stage {
    public static final LoginScreen INSTANCE = new LoginScreen();
    private TextField usernameTextField;
    private PasswordField passwordField;
    private Text errorText;
    private Stage primaryStage;

    private final LoginController controller;

    private LoginScreen() {
        controller = DIControllers.createLoginController();
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Scene scene = getSceneBody();
        // Create the input fields
        setScene(scene);
        show();
    }

    private Scene getSceneBody() {
        setTitle("Login Screen");

        // Create the header
        Text headerText = new Text("Login");
        headerText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        StackPane headerPane = new StackPane(headerText);
        headerPane.setPadding(new Insets(50));
        headerPane.setBackground(new Background(new BackgroundFill(Color.web("#323232"), CornerRadii.EMPTY, Insets.EMPTY)));
        Label usernameLabel = new Label("Username:");
        usernameLabel.setTextFill(Color.WHITE);
        usernameTextField = new TextField();
        usernameTextField.setPrefWidth(250);
        usernameTextField.setMaxWidth(250);
        Label passwordLabel = new Label("Password:");
        passwordLabel.setTextFill(Color.WHITE);
        passwordField = new PasswordField();
        passwordField.setPrefWidth(250);
        passwordField.setMaxWidth(250);
        GridPane inputGridPane = new GridPane();
        inputGridPane.add(usernameLabel, 0, 0);
        inputGridPane.add(usernameTextField, 1, 0);
        inputGridPane.add(passwordLabel, 0, 1);
        inputGridPane.add(passwordField, 1, 1);
        inputGridPane.setHgap(10);
        inputGridPane.setVgap(10);
        inputGridPane.setPadding(new Insets(25));
        inputGridPane.setBackground(new Background(new BackgroundFill(Color.web("#3d3d3d"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Create the error text
        errorText = new Text();
        errorText.setFill(Color.RED);

        // Create the login button
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14pt; -fx-padding: 10px 20px;");
        loginButton.setOnAction(
                event -> validateCredentials(
                        usernameTextField.getText(),
                        passwordField.getText()
                )
        );

        // Create the footer
        HBox footerBox = new HBox(errorText, loginButton);
        footerBox.setAlignment(Pos.CENTER_RIGHT);
        footerBox.setSpacing(10);
        footerBox.setPadding(new Insets(25));
        footerBox.setBackground(new Background(new BackgroundFill(Color.web("#323232"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Create the main layout
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(headerPane);
        borderPane.setCenter(inputGridPane);
        borderPane.setBottom(footerBox);

        return new Scene(borderPane, 800, 600);
    }

    private void validateCredentials(String username, String password) {
        if (!controller.isValidaSignIn(username, password)) {
            errorText.setText("");
            errorText.setText("Invalid username or password");
        }
        controller.signIn(username, password).apply(
                user -> {
                    errorText.setText("");
                    close();
                    openMainApplicationWindow();
                },
                fail -> {
                    errorText.setText("");
                    errorText.setText(fail.getMessage());
                }
        );
    }

    private void openMainApplicationWindow() {
        var home = HomeScreen.INSTANCE;
        home.setStage(primaryStage);
        home.show();
    }

}