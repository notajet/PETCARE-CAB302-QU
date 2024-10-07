package com.example.petcarecab302qu.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controller class for managing the navigation bar in the PETHUB application.
 * Loads the navigation bar from an FXML file and adds it to the view.
 */
public class NavigationController {

    @FXML
    protected VBox navigationBar;

    /**
     * Loads the navigation bar from the FXML file and adds it to the VBox.
     * This method is responsible for initialising and setting up the navigation bar.
     */
    protected void NavigationBar(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/petcarecab302qu/navigation-bar.fxml"));
            VBox navigation = loader.load();
            navigationBar.getChildren().add(navigation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}