package com.example.petcarecab302qu.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import com.example.petcarecab302qu.util.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.Connection;
import javafx.scene.control.*;
import javafx.scene.text.Text;


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
    private ToggleGroup toggleGroup;


    @FXML
    public void initialize(){
        walkRadioButton.setToggleGroup(toggleGroup);
        runRadioButton.setToggleGroup(toggleGroup);
        playRadioButton.setToggleGroup(toggleGroup);

        walkRadioButton.setSelected(true);
    }

    private String exerciseType;

    @FXML
    public void handleExerciseRadioButton(){
        if (walkRadioButton.isSelected()){
            exerciseType = "walk";
        } else if (runRadioButton.isSelected()){
            exerciseType = "run";
        }else if (playRadioButton.isSelected()){
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
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/dietplan-view.fxml");

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