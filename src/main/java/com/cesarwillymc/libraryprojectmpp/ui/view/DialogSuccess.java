package com.cesarwillymc.libraryprojectmpp.ui.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DialogSuccess {
    public DialogSuccess(String title, String subTitle, Runnable runnable) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(subTitle);

        ButtonType closeButton = new ButtonType("Okey", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(closeButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == closeButton) {
            runnable.run();
            alert.close();
        }
    }
}
