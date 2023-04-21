package com.cesarwillymc.libraryprojectmpp.ui.home.menu;

import com.cesarwillymc.libraryprojectmpp.ui.home.DashboardView;
import javafx.scene.layout.BorderPane;

public class MyMenuImpl implements MyMenu {
    // Referencia a la estructura de la interfaz de usuario
    private BorderPane root;

    // Constructor que inicializa la estructura de la interfaz de usuario
    public MyMenuImpl(BorderPane root) {
        this.root = root;
    }

    @Override
    public void showDashboard() {
        // Actualizar el área central con las gráficas de los préstamos
        // de usuarios y libros
        root.setCenter(new DashboardView().getNode());
    }

    @Override
    public void showBooks() {
        // Actualizar el área central con la información de los libros
        root.setCenter(new DashboardView().getNode());
    }

    @Override
    public void showMembers() {
        // Actualizar el área central con la información de los miembros
        root.setCenter(new DashboardView().getNode());
    }

    @Override
    public void showProfile() {
        // Actualizar el área central con la información del perfil del usuario
        root.setCenter(new DashboardView().getNode());
    }
}