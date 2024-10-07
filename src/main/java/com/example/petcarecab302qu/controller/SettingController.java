package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.util.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Controller class for managing the Setting's functionality in the Pet Care application.
 */
public class SettingController extends NavigationController{


    @FXML
    private ImageView logoImage;

    @FXML
    private CheckBox enableNotifications;

    @FXML
    private CheckBox enableMusic;

    @FXML
    private ComboBox<String> languageSelection;

    @FXML
    private CheckBox shareDataCheckBox;

    @FXML
    private CheckBox allowLocationCheckBox;

    @FXML
    private CheckBox syncCheckBox;

    /**
     * Initializes the settings page, loads the navigation bar, header, and sets the default checkbox and combo box
     */
    @FXML
    public void initialize() {
        NavigationBar();
        loadLogoImage();

        enableNotifications.setSelected(true);
        enableMusic.setSelected(false);
        languageSelection.getSelectionModel().select("English");
        shareDataCheckBox.setSelected(false);
        allowLocationCheckBox.setSelected(false);
        syncCheckBox.setSelected(true);
    }

    /**
     * Loads the logo image for the header section.
     */
    private void loadLogoImage() {
        if (logoImage != null) {
            Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logo.png")));
            logoImage.setImage(logo);
        }
    }


    public void handleLogOutButton(ActionEvent event){
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/home-view.fxml");
    }

}
