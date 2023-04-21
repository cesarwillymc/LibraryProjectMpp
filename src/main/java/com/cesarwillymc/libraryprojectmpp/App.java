package com.cesarwillymc.libraryprojectmpp;

import com.cesarwillymc.libraryprojectmpp.ui.HomeScreen;
import com.cesarwillymc.libraryprojectmpp.ui.login.LoginScreen;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        // Load splash screen image
        Image splashImage = new Image("https://www.pngitem.com/pimgs/m/19-194725_icon-of-a-stack-of-colorful-books-with.png");
        ImageView splashImageView = new ImageView(splashImage);

        // Add fade transition to splash screen image
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), splashImageView);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setOnFinished(event -> {
            // Close splash screen and open main application window
            primaryStage.close();
            openLoginApplicationWindow();


        });

        StackPane splashLayout = new StackPane(splashImageView);
        Scene splashScene = new Scene(splashLayout);
        splashScene.setFill(null);
        stage.setScene(splashScene);
        stage.show();
        fadeTransition.play();
    }

    private void openMainApplicationWindow() {
        var home=HomeScreen.INSTANCE;
        home.setStage(primaryStage);
        home.show();
    }

    private void openLoginApplicationWindow() {
        var login=LoginScreen.INSTANCE;
        login.setStage(primaryStage);
        login.show();
    }


    public static void main(String[] args) {
        launch();
    }
}