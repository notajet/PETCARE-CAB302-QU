package com.example.petcarecab302qu;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
         welcomeText.setText("I like turtles!!!!!!!!!!!!!!!!!!!!");

    }
}