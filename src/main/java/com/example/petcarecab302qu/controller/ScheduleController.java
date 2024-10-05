package com.example.petcarecab302qu.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Controller class for managing the Scheduling Functionality in the Pet Care application.
 */
public class ScheduleController extends NavigationController {

    @FXML
    private ImageView logoImage;

    @FXML
    public void initialize() {
        NavigationBar();
        loadLogoImage();
    }

    private void loadLogoImage() {
        if (logoImage != null) {
            Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logo.png")));
            logoImage.setImage(logo);
        }
    }
}
