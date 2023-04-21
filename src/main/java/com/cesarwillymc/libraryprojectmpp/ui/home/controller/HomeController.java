package com.cesarwillymc.libraryprojectmpp.ui.home.controller;

import com.cesarwillymc.libraryprojectmpp.ui.home.menu.MyMenu;

public class HomeController {
    private MyMenu view;

    // Constructor que recibe la vista como parámetro
    public HomeController(MyMenu view) {
        this.view = view;
    }

    // Método que se llama cuando se selecciona la opción "Dashboard" del menú
    public void onDashboardSelected() {
        view.showDashboard();
    }

    // Método que se llama cuando se selecciona la opción "Book" del menú
    public void onBooksSelected() {
        view.showBooks();
    }

    // Método que se llama cuando se selecciona la opción "Member" del menú
    public void onMembersSelected() {
        view.showMembers();
    }

    // Método que se llama cuando se selecciona la opción "Profile" del menú
    public void onProfileSelected() {
        view.showProfile();
    }
}
