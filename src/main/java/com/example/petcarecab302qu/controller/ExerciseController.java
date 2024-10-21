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

    private IExerciseDAO exerciseDAO;

    private PetSelectionVbox petSelectionVbox;

    @FXML
    public ListView<String> recentExerciseList;

    public ExerciseController() {
        this.exerciseDAO = new SqliteExerciseDAO(); // Use the default implementation
    }

    // Constructor for testing or dependency injection
    public ExerciseController(IExerciseDAO exerciseDAO) {
        this.exerciseDAO = exerciseDAO;
    }

    /**
     * Initialize the navigation bar and configures the toggle group of radio buttons for exercise type
     */
    @FXML
    public void initialize(){

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

        loadRecentExercises();

    }

    /**
     * Loads recently logged exercises from the database
     */
    public void loadRecentExercises() {
        // Get the exercises from the database
        List<Exercise> recentExercises = exerciseDAO.getAllExercises();


        for (Exercise exercise : recentExercises) {

            String petName = exercise.getPetName();

            String displayExercise =  petName + ":" + exercise.getType() + " for " + exercise.getDuration() + " minutes on " + exercise.getDate();
            recentExerciseList.getItems().add(displayExercise);
        }
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

        String dateToString = date.toString();

        String selectedPetName = petSelectionVbox.getSelectedPet().getName();


        Exercise logExercise = new Exercise(selectedPetName,dateToString, exerciseType, duration, notes);
        exerciseDAO.addExercise(logExercise);

        String displayLoggedExercise = selectedPetName + ":" + exerciseType + " for " + duration + " minutes on " + dateToString;

        recentExerciseList.getItems().add(displayLoggedExercise);


        confirmMessage.setText("Exercise logged successfully!");
        confirmMessage.setVisible(true);


        notesArea.clear();
        minuteSpinner.getValueFactory().setValue(0);
        exerciseType = null;
        errorMessage.setVisible(false); // Hide previous messages

    }

}