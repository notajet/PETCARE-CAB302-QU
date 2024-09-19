package com.example.petcarecab302qu.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import com.example.petcarecab302qu.util.SceneLoader;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.fxml.FXMLLoader;
import java.io.IOException;


public class ExerciseController extends NavigationController {

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

        NavigationBar();

        ToggleGroup typeOfExercise = new ToggleGroup();
        walkRadioButton.setToggleGroup(typeOfExercise);
        runRadioButton.setToggleGroup(typeOfExercise);
        playRadioButton.setToggleGroup(typeOfExercise);
    }

    private void NavigationBar(){
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


    /*
    public handleSaveExerciseButton(){

    }*/

}
