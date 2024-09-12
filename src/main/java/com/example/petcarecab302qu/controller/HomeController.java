package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    public void handleUserProfile(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/petcarecab302qu/hello-view.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/petcarecab302qu/login-view.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
            stage.setScene(scene);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleSignUpAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/petcarecab302qu/signup-view.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
            stage.setScene(scene);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public Button exitButton;

    @FXML
    public void handleExitButtonAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void handlePetProfile(ActionEvent event) {
        // TODO
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/petcarecab302qu/petprofile-view.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
            stage.setScene(scene);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void handleDietPlan(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/petcarecab302qu/diet-view.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
            stage.setScene(scene);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void handleExercise(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/petcarecab302qu/exercise-view.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
            stage.setScene(scene);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleSchedule(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/petcarecab302qu/schedule-view.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
            stage.setScene(scene);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleSetting(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/petcarecab302qu/setting-view.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
            stage.setScene(scene);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



}


