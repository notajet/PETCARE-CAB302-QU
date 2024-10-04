package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.util.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller class for handling the main home view in the Pet Care application.
 * Extends the NavigationController to provide navigation functionality and handle user actions such as logging out.
 */
public class HomeMainController extends NavigationController {

    @FXML
    private ImageView backgroundImage;

    @FXML
    public void initialize(){

        NavigationBar();

        Image background = new Image(getClass().getResourceAsStream("/images/background.png"));
        backgroundImage.setImage(background);
    }

    public void handleLogOutButton(ActionEvent event){
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/home-view.fxml");
    }
}
