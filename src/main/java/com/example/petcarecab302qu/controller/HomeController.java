package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.HelloApplication;
import com.example.petcarecab302qu.util.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController  {

    /**
     *
     * @param event
     */
    @FXML
    public void handleLogin(ActionEvent event) {
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/login-view.fxml");
    }

    @FXML
    public void handleSignUpAction(ActionEvent event) {
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/signup-view.fxml");
    }

    @FXML
    public Button exitButton;

    @FXML
    public void handleExitButtonAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void handleHome(ActionEvent event) {
        SceneLoader.loadScene(event,"/com/example/petcarecab302qu/homemain-view.fxml");
    }

    public void handlePetProfile(ActionEvent event) {
        SceneLoader.loadScene(event,"/com/example/petcarecab302qu/petprofile-view.fxml");
    }

    public void handleDietPlan(ActionEvent event) {
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/diet-view.fxml");

    }

    public void handleExercise(ActionEvent event){
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/exercise-view.fxml");

    }

    public void handleSchedule(ActionEvent event){
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/schedule-view.fxml");

    }

    public void handleSetting(ActionEvent event){
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/setting-view.fxml");

    }

}


