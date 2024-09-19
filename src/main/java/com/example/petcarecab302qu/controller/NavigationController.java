package com.example.petcarecab302qu.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class NavigationController {

    @FXML
    protected VBox navigationBar;

    protected void NavigationBar(){
        try {
            // Load the navigation FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/petcarecab302qu/navigation-bar.fxml"));
            VBox navigation = loader.load();
            // Add the navigation to the navigation container
            navigationBar.getChildren().add(navigation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
