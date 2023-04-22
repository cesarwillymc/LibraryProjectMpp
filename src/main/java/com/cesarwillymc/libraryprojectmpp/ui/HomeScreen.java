package com.cesarwillymc.libraryprojectmpp.ui;

import com.cesarwillymc.libraryprojectmpp.ui.books.BookView;
import com.cesarwillymc.libraryprojectmpp.ui.home.DashboardView;
import com.cesarwillymc.libraryprojectmpp.ui.home.view.Menu;
import com.cesarwillymc.libraryprojectmpp.ui.login.LoginScreen;
import com.cesarwillymc.libraryprojectmpp.ui.members.MemberView;
import com.cesarwillymc.libraryprojectmpp.ui.profile.ProfileView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeScreen extends Stage {
    public static HomeScreen INSTANCE = new HomeScreen();
    private Menu menu;
    private DashboardView dashboardView;
    private BookView bookView;
    private MemberView memberView;
    private ProfileView profileView;
    private Stage primaryStage;

    public void setStage(Stage stage) {
        primaryStage = stage;
        // Create instances of all the views
        dashboardView = new DashboardView();
        bookView = new BookView(()->{

        },(s)->{

        });
        memberView = new MemberView(()->{

        }, (S)->{

        });
        profileView = new ProfileView(()->{
            close();
            var login = LoginScreen.INSTANCE;
            login.setStage(primaryStage);
            login.show();
        });
        menu = new Menu(dashboardView, bookView, memberView, profileView);

        menu.switchToDashboard();

        Scene scene = new Scene((Parent) menu.getNode(), 800, 600);
        setScene(scene);
        show();
    }

    private HomeScreen() {
    }

}
