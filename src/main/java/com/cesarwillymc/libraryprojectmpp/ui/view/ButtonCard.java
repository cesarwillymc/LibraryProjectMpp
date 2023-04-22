package com.cesarwillymc.libraryprojectmpp.ui.view;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;

import java.util.function.Consumer;

public class ButtonCard extends StackPane {

    private ImageView arrowIcon;
    private Label label;
    Button button = new Button();
    Boolean isReverse =false;
    public ButtonCard(String text, Consumer<Boolean> onclick) {
        // create arrow icon
        Image downArrow = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Arrow-down.svg/1280px-Arrow-down.svg.png",30,30,true,false);

        arrowIcon = new ImageView(downArrow);

        // create label
        label = new Label(text);

        // create button
        Button button = new Button();
        button.getStyleClass().add("button-card");
        button.setOnAction(event -> {
            if (arrowIcon.getImage().equals(downArrow)) {
                Rotate rotation = new Rotate(180, downArrow.getWidth() / 2, downArrow.getHeight() / 2);
                arrowIcon.getTransforms().add(rotation);
                // handle up arrow click
            } else {
                Rotate rotation = new Rotate(180, downArrow.getWidth() / 2, downArrow.getHeight() / 2);
                arrowIcon.getTransforms().add(rotation);
                // handle down arrow click
            }
            isReverse = !isReverse;
            onclick.accept(isReverse);
        });

        // add arrow icon and label to layout
        HBox content = new HBox();
        content.setAlignment(Pos.CENTER);
        content.getChildren().addAll(arrowIcon, label);

        // add layout to button
        button.setGraphic(content);

        // add button to this card
        getChildren().add(button);
    }


    public Boolean getReverse() {
        return isReverse;
    }
}
