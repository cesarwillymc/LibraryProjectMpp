package com.cesarwillymc.libraryprojectmpp.ui;

import com.cesarwillymc.libraryprojectmpp.ui.books.AddBookScreen;
import com.cesarwillymc.libraryprojectmpp.ui.books.BookDetailScreen;
import com.cesarwillymc.libraryprojectmpp.ui.books.BookView;
import com.cesarwillymc.libraryprojectmpp.ui.checkoutBook.CheckoutBookView;
import com.cesarwillymc.libraryprojectmpp.ui.home.DashboardView;
import com.cesarwillymc.libraryprojectmpp.ui.home.view.Menu;
import com.cesarwillymc.libraryprojectmpp.ui.login.LoginScreen;
import com.cesarwillymc.libraryprojectmpp.ui.members.AddMemberScreen;
import com.cesarwillymc.libraryprojectmpp.ui.members.DetailMemberScreen;
import com.cesarwillymc.libraryprojectmpp.ui.members.MemberView;
import com.cesarwillymc.libraryprojectmpp.ui.profile.ProfileView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeScreen extends Stage {
    public static final HomeScreen INSTANCE = new HomeScreen();
    private Stage primaryStage;

    public void setStage(Stage stage) {
        primaryStage = stage;
        // Create instances of all the views
        DashboardView dashboardView = new DashboardView();
        BookView bookView = new BookView(() -> {

            var addBookScreen = AddBookScreen.INSTANCE;
            hide();
            addBookScreen.setStage(this);
            addBookScreen.show();
        }, (s) -> {

            var bookDetailScreen = BookDetailScreen.INSTANCE;
            hide();
            bookDetailScreen.setStage(this, s);
            bookDetailScreen.show();
        });
        MemberView memberView = new MemberView(() -> {
            var addMemberScreen = AddMemberScreen.INSTANCE;
            hide();
            addMemberScreen.setStage(this);
            addMemberScreen.show();
        }, (S) -> {
            var detailMember = DetailMemberScreen.INSTANCE;
            hide();
            detailMember.setStage(this, S);
            detailMember.show();
        });
        ProfileView profileView = new ProfileView(() -> {
            close();
            var login = LoginScreen.INSTANCE;
            login.setStage(primaryStage);
            login.show();
        });

        CheckoutBookView checkoutBookView = new CheckoutBookView();
        Menu menu = new Menu(dashboardView, bookView, memberView, profileView, checkoutBookView);

        menu.switchToDashboard();

        Scene scene = new Scene((Parent) menu.getNode(), 800, 600);
        setScene(scene);
        show();
    }

    private HomeScreen() {
    }

}
