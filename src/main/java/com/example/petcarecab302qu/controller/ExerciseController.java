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
    private SqlitePetDAO petDAO = new SqlitePetDAO();

    private PetSelectionVbox petSelectionVbox;

    @FXML
    private ListView<HBox> recentExerciseList;

    /**
     * Initialize the navigation bar and configures the toggle group of radio buttons for exercise type
     */
    @FXML
    public void initialize(){

        exerciseDAO = new SqliteExerciseDAO();
        petDAO = new SqlitePetDAO();

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


        // Get the currently selected pet from PetSelectionVbox
        Pet selectedPet = petSelectionVbox.getSelectedPet();


        if (selectedPet == null) {
            errorMessage.setText("Please select a pet.");
            errorMessage.setVisible(true);
            return;
        }

        System.out.println("Selected pet: " + selectedPet.getName());

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

        Exercise logExercise = new Exercise(selectedPet.getName(), stringDate, exerciseType, duration, notes);
        exerciseDAO.addExercise(logExercise);

        notesArea.clear();
        minuteSpinner.getValueFactory().setValue(0);
        exerciseType = null;
        errorMessage.setVisible(false); // Hide previous messages


        // Display confirmation message
        confirmMessage.setText("Exercise logged successfully!");
        confirmMessage.setVisible(true);

        // Update the ListView to display the most recent exercise
        displayMostRecentExercise(selectedPet.getName());
    }


    /**
     * Fetches and displays the most recent exercises for the selected pet in the ListView.
     *
     * @param petName The name of the pet.
     */
    private void displayMostRecentExercise(String petName) {
        List<Exercise> exercises = exerciseDAO.getExercisesByPet(petName);
        recentExerciseList.getItems().clear();

        for (Exercise exercise : exercises) {
            HBox exerciseBox = createExerciseBox(exercise);
            recentExerciseList.getItems().add(exerciseBox);  // Add HBox to ListView
        }
    }

    /**
     * Creates an HBox representing a single exercise.
     *
     * @param exercise The exercise data
     * @return An HBox containing the exercise details
     */
    private HBox createExerciseBox(Exercise exercise) {
        Label typeLabel = new Label("Type: " + exercise.getType());
        Label durationLabel = new Label("Duration: " + exercise.getDuration() + " mins");
        Label dateLabel = new Label("Date: " + exercise.getDate());

        HBox hbox = new HBox(20);
        hbox.getChildren().addAll(typeLabel, durationLabel, dateLabel);
        return hbox;
    }
}