package com.example.petcarecab302qu.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import com.example.petcarecab302qu.util.SceneLoader;
import javafx.scene.control.*;
import javafx.scene.text.*;


public class ExerciseController {
    @FXML
    private ProgressBar progressBar;

    @FXML
    private RadioButton walkRadioButton;

    @FXML
    private RadioButton runRadioButton;

    @FXML
    private RadioButton playRadioButton;

    @FXML
    private Spinner<Integer> hourSpinner;

    @FXML
    private Spinner<Integer> minuteSpinner;

    @FXML
    private TextArea notesArea;

    @FXML
    private Text errorMessage;

    @FXML
    public void initialize(){
        ToggleGroup typeOfExercise = new ToggleGroup();
        walkRadioButton.setToggleGroup(typeOfExercise);
        runRadioButton.setToggleGroup(typeOfExercise);
        playRadioButton.setToggleGroup(typeOfExercise);
    }

    private String exerciseType;

    @FXML
    public void handleExerciseRadioButton(ActionEvent event){
        if (walkRadioButton.isSelected()){
            walkRadioButton.setSelected(true);
            exerciseType = "walk";
        } else if (runRadioButton.isSelected()){
            runRadioButton.setSelected(true);
            exerciseType = "run";
        }else if (playRadioButton.isSelected()){
            playRadioButton.setSelected(true);
            exerciseType = "play";
        }
    }

    @FXML
    public void handleSaveExerciseButton(){

        int hours = hourSpinner.getValue();
        int minutes = minuteSpinner.getValue();
        int totalDuration = (hours * 60) + minutes;

        if (exerciseType == null || exerciseType.isEmpty()) {
            errorMessage.setText("Please select an exercise type.");
            errorMessage.setStyle("-fx-text-fill: red;");
            errorMessage.setVisible(true);
            return;
        }
        // Clear the input fields
        notesArea.clear();
        hourSpinner.getValueFactory().setValue(0);
        minuteSpinner.getValueFactory().setValue(0);
        exerciseType = null; // Reset exercise type
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

    public void handleHome(ActionEvent event) {
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/homemain-view.fxml");
    }
}
