package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.model.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;

import java.util.List;
import java.util.Objects;

/**
 * Manages user interface for logging pet exercises
 */

public class ExerciseController extends NavigationController {

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
    private VBox petsContainer;  // The container for auto adding pet profiles


    //@FXML
    //private Label petNameLabel;

    @FXML
    private ImageView petImageView;  // Existing ImageView for displaying the selected pet's image

    private String exerciseType;

    private Button previousSelectedButton;  // To keep track of the previously selected button

    private SqliteExerciseDAO exerciseDAO = new SqliteExerciseDAO();
    private SqlitePetDAO petDAO = new SqlitePetDAO();
    private List<Pet> pets;  // List of pets for the logged-in user
    private Pet selectedPet;  // The currently selected pet

    /**
     * Initialize the navigation bar and configures the toggle group of radio buttons for exercise type
     */
    @FXML
    public void initialize(){
        exerciseDAO = new SqliteExerciseDAO();

        NavigationBar();

        ToggleGroup typeOfExercise = new ToggleGroup();
        walkRadioButton.setToggleGroup(typeOfExercise);
        runRadioButton.setToggleGroup(typeOfExercise);
        playRadioButton.setToggleGroup(typeOfExercise);

        loadPetProfiles();
    }

    /**
     * Loads the pet profiles from the database and creates a button for each pet
     * Each button will be in a circle, displaying the pet's image or a default image
     */
    private void loadPetProfiles() {
        pets = petDAO.getAllPets();

        // Create a circular button for each pet and add it to the petsContainer
        for (Pet pet : pets) {
            Button petButton = new Button();

            // Create the ImageView to display the pet's image
            ImageView petImageView = new ImageView();
            petImageView.setFitHeight(40);
            petImageView.setFitWidth(40);
            petImageView.setPreserveRatio(true);

            Circle clip = new Circle(20, 20, 20);  // Circle radius to match the size of the ImageView
            petImageView.setClip(clip);

            // Load the pet's image or default image if none provided
            if (pet.getImageUrl() != null && !pet.getImageUrl().isEmpty()) {
                try {
                    Image petImage = new Image(pet.getImageUrl());
                    petImageView.setImage(petImage);
                } catch (Exception e) {
                    petImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/default_pet.png"))));
                }
            } else {
                petImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/default_pet.png"))));
            }

            // Add the ImageView to the button
            petButton.setGraphic(petImageView);

            defaultButton(petButton);



            petButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectPet(pet, petButton);
                }
            });

            // Add the button to the petsContainer
            petsContainer.getChildren().add(petButton);
        }
    }

    /**
     * Default styling for pet buttons
     */
    private void defaultButton(Button button) {
        button.setStyle(null);
        button.setStyle("-fx-background-radius: 50%; -fx-border-radius: 50%; -fx-border-width: 2px; -fx-padding: 2px;");
    }

    /**
     * Highlighted styling for the selected pet button
     */
    private void highlightButton(Button button) {
        button.setStyle(null);
        button.setStyle("-fx-background-color: lightblue; -fx-border-color: blue; -fx-border-width: 5px; -fx-padding: 3px;");
    }

    /**
     * Handles pet selection when a pet button is clicked it highlights the selected pet
     * @param pet selected pet
     * @param petButton button connected to the selected pet
     */

    private void selectPet(Pet pet, Button petButton) {
        selectedPet = pet;

        // Load and display the pet image or default image if none provided
        if (pet.getImageUrl() != null && !pet.getImageUrl().isEmpty()) {
            try {
                Image petImage = new Image(pet.getImageUrl());
                petImageView.setImage(petImage);
            } catch (Exception e) {
                // If image loading fails
                petImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/default_pet.png"))));
            }
        } else {
            petImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/default_pet.png"))));
        }

        if (previousSelectedButton != null) {
            defaultButton(previousSelectedButton);
        }

        highlightButton(petButton);
        previousSelectedButton = petButton;

        // Update selected pet's name
        //petNameLabel.setText(pet.getName());
    }

    /**
     * Handles the logging of exercises when save button is pressed.
     * adds the exercise info to the database
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
        errorMessage.setVisible(false); // Hide previous messages


        // Display confirmation message
        confirmMessage.setText("Exercise logged successfully!");
        confirmMessage.setVisible(true);

    }


    /*
        // Reset the style of the previous selected button
        if (previousSelectedButton != null) {
            previousSelectedButton.setStyle(
                    "-fx-background-radius: 50%; " +     // Circular background
                            "-fx-border-radius: 50%; " +         // Circular border
                            "-fx-border-color: white; " +        // Reset to black border
                            "-fx-border-width: 1.5px; " +        // Standard border width
                            "-fx-padding: 3px; "                 // Standard padding
            );
        }

        // Highlight the selected button
        petButton.setStyle(
                "-fx-background-radius: 50%; " +             // Circular background
                        "-fx-border-radius: 50%; " +                 // Circular border
                        "-fx-border-color: blue; " +                 // Bright blue border to indicate selection
                        "-fx-border-width: 5px; " +                  // Thicker border for the selected button
                        "-fx-background-color: lightblue; " +        // Change background color to light blue
                        "-fx-padding: 3px;"                          // Padding remains
        );

        */


    /*
    public handleSaveExerciseButton(){

    }*/

}