package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private TextArea termsAndConditions;
    @FXML
    private CheckBox agreeCheckBox;
    @FXML
    private Button nextButton;

    @FXML
    public void initialize() {
        termsAndConditions.setText("""
                Add your profile at your own discretion. We are not encrypted,
                hence your personal data could be stolen at anytime. Be warned!""");
    }

    /**
     * A Method that controls the TODO
     */
    @FXML
    protected void onAgreeCheckBoxClick() {
        boolean accepted = agreeCheckBox.isSelected();
        nextButton.setDisable(!accepted);
    }

}