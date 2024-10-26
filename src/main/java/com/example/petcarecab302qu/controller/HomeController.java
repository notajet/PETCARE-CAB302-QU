package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.util.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Controller class for handling user interactions in the home screen of the PETHUB application.
 */
public class HomeController extends NavigationController {

    @FXML
    public ImageView logoImage;

    /**
     * Initialises the home screen, setting the logo image if available.
     */
    @FXML
    public void initialize() {
        if (logoImage != null) {
            Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logo.png")));
            logoImage.setImage(logo);
        }
    }

    /**
     *
     * @param event triggered by login button
     */
    @FXML
    public void handleLogin(ActionEvent event) {
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/login-view.fxml");
    }

    /**
     *
     * @param event triggered by sign up button
     */
    @FXML
    public void handleSignUpAction(ActionEvent event) {
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/signup-view.fxml");
    }

    /**
     * The exit button used to close the application window.
     */
    @FXML
    public Button exitButton;

    /**
     *
     * @param event triggered by exit button
     */
    @FXML
    public void handleExitButtonAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}

