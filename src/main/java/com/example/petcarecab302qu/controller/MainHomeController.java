package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.util.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Controller class for handling the main home view in the Pet Care application.
 * Extends the NavigationController to provide navigation functionality and handle user actions such as logging out.
 */
public class MainHomeController extends NavigationController {

    @FXML
    private ImageView backgroundImage;
    @FXML
    public ImageView logoImage;

    /**
     * Initializes the UI elements for the controller.
     * Sets up the navigation bar and loads images for the logo and background.
     * This method is called automatically after the FXML fields are injected.
     */
    @FXML
    public void initialize(){

        NavigationBar();

        if (logoImage != null) {
            Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logo.png")));
            logoImage.setImage(logo);
        }
        Image background = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/background.png")));
        backgroundImage.setImage(background);
    }

    /**
     * Handles the action for logging out and returning to the home screen.
     * Uses the SceneLoader utility to navigate back to the home view.
     *
     * @param event The action event triggered by the logout button.
     */
    public void handleLogOutButton(ActionEvent event){
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/home-view.fxml");
    }
}
