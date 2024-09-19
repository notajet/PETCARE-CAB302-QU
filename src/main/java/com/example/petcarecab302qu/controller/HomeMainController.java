package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.util.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class HomeMainController extends NavigationController {

    @FXML
    public void initialize(){

        NavigationBar();
    }

    public void handleLogOutButton(ActionEvent event){
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/home-view.fxml");
    }
}
