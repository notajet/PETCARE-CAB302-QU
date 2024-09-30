package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.util.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller class for handling user interactions in the home screen of the PETHUB application.
 */

public class HomeController  {

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

    /**
     *
     * @param event triggered by home button
     */
    public void handleHome(ActionEvent event) {
        SceneLoader.loadScene(event,"/com/example/petcarecab302qu/homemain-view.fxml");
    }

    /**
     *
     * @param event triggered by pet profile button
     */
    public void handlePetProfile(ActionEvent event) {
        SceneLoader.loadScene(event,"/com/example/petcarecab302qu/petprofile-view.fxml");
    }

    /**
     *
     * @param event triggered by diet button
     */
    public void handleDietPlan(ActionEvent event) {
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/diet-view.fxml");

    }
    /**
     *
     * @param event triggered by exercise button
     */

    public void handleExercise(ActionEvent event){
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/exercise-view.fxml");
    }

    /**
     *
     * @param event triggered by schedule button
     */
    public void handleSchedule(ActionEvent event){
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/schedule-view.fxml");

    }

    /**
     *
     * @param event triggered by setting button
     */
    public void handleSetting(ActionEvent event){
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/setting-view.fxml");

    }

}


