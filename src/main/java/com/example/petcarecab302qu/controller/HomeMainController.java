package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.util.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class HomeMainController extends NavigationController {
    /**
     * Sets up the navigation bar
     */
    @FXML
    public void initialize(){

        NavigationBar();
    }

    /**
     * handles the action of logging the user out when logout button is pressed
     * loads back to starting page
     * @param event triggered by the logout button
     */
    public void handleLogOutButton(ActionEvent event){
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/home-view.fxml");
    }
}
