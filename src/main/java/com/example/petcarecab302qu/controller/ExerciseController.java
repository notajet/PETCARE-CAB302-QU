package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import java.time.LocalDate;

import java.util.List;
import java.util.Objects;



public class ExerciseController extends NavigationController {

    @FXML
    public ImageView logoImage;

    @FXML
    private RadioButton walkRadioButton;

    @FXML
    private RadioButton runRadioButton;

    @FXML
    private RadioButton playRadioButton;

    @FXML
    private Spinner<Integer> minuteSpinner;

    @FXML
    private TextArea notesArea;

    @FXML
    private Text errorMessage;

    @FXML
    private Text confirmMessage;

    @FXML
    private VBox petBox;

    private String exerciseType;

    private SqliteExerciseDAO exerciseDAO = new SqliteExerciseDAO();

    private PetSelectionVbox petSelectionVbox;

    /**
     * Initialize the navigation bar and configures the toggle group of radio buttons for exercise type
     */
    @FXML
    public void initialize(){

        exerciseDAO = new SqliteExerciseDAO();

        NavigationBar();
        //logo image
        if (logoImage != null) {
            Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logo.png")));
            logoImage.setImage(logo);
        }

        // Create the pet selection box and add it to the view
        petSelectionVbox = new PetSelectionVbox(petBox);


        ToggleGroup typeOfExercise = new ToggleGroup();
        walkRadioButton.setToggleGroup(typeOfExercise);
        runRadioButton.setToggleGroup(typeOfExercise);
        playRadioButton.setToggleGroup(typeOfExercise);

    }



    /**
     * Handles the logging of exercises when save button is pressed.
     * adds the exercise info to the database
     */
    @FXML
    public void handleSaveExerciseButton(){

        LocalDate date = LocalDate.now();

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

        String stringDate = date.toString();

        //NEED THE PET ID//////

        Exercise logExercise = new Exercise(stringDate, exerciseType, duration, notes);
        exerciseDAO.addExercise(logExercise);

        notesArea.clear();
        minuteSpinner.getValueFactory().setValue(0);
        exerciseType = null;
        errorMessage.setVisible(false); // Hide previous messages


        // Display confirmation message
        confirmMessage.setText("Exercise logged successfully!");
        confirmMessage.setVisible(true);

    }

}