package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.model.*;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import com.example.petcarecab302qu.util.SceneLoader;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

/**
 * Manages user interface for logging pet exercises
 */

public class ExerciseController extends NavigationController{

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

    private String exerciseType;

    private SqliteExerciseDAO exerciseDAO;

    /**
     * Initializes the navigation bar and configures the toggle group for exercise type
     */
    @FXML
    public void initialize(){
        exerciseDAO = new SqliteExerciseDAO();

        NavigationBar();

        ToggleGroup typeOfExercise = new ToggleGroup();
        walkRadioButton.setToggleGroup(typeOfExercise);
        runRadioButton.setToggleGroup(typeOfExercise);
        playRadioButton.setToggleGroup(typeOfExercise);
    }

    /**
     * Handles the logging of exercises when save button is pressed.
     * Validates users input and adds exercise to the database
     */
    @FXML
    public void handleSaveExerciseButton(){
        if (walkRadioButton.isSelected()){
            exerciseType = "walk";
        } else if (runRadioButton.isSelected()){
            exerciseType = "run";
        }else if (playRadioButton.isSelected()){
            exerciseType = "play";
        }

        if (exerciseType == null){
            errorMessage.setText("Please select an exercise.");
            errorMessage.setVisible(true);
            return;
        }

        int duration = minuteSpinner.getValue();
        if (duration == 0) {
            errorMessage.setText("Please enter a valid duration.");
            errorMessage.setVisible(true);
            return;
        }

        String notes = notesArea.getText(); //optional

        Exercise logExercise = new Exercise(exerciseType, duration, notes);
        exerciseDAO.addExercise(logExercise);

        notesArea.clear();
        minuteSpinner.getValueFactory().setValue(0);
        exerciseType = null;
    }


    /*
    public handleSaveExerciseButton(){

    }*/

}
