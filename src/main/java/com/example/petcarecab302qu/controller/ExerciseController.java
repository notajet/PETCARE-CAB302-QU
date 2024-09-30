package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.model.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import com.example.petcarecab302qu.util.SceneLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

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
    private Spinner<Integer> minuteSpinner;

    @FXML
    private TextArea notesArea;

    @FXML
    private Text errorMessage;

    @FXML
    private VBox petsContainer;  // The container for auto adding pet profiles

    //@FXML
    //private Label petNameLabel;

    @FXML
    private ImageView petImageView;  // Existing ImageView for displaying the selected pet's image

    private String exerciseType;

    private SqliteExerciseDAO exerciseDAO = new SqliteExerciseDAO();
    private SqlitePetDAO petDAO = new SqlitePetDAO();
    private List<Pet> pets;  // List of pets for the logged-in user
    private Pet selectedPet;  // The currently selected pet

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

        loadPetProfiles();
    }

    /**
     * Loads the pet profiles from the database and creates a button for each pet.
     * Each button will be circular and display the pet's image or a default image.
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


            // Style the button to be circular, with a stroke
            petButton.setStyle(
                    "-fx-background-radius: 50%; " +     // Circular background
                            "-fx-border-radius: 50%; " +         // Circular border
                            "-fx-border-color: black; " +        // Border color
                            "-fx-border-width: 2px; " +          // Border width
                            "-fx-padding: 2px; "                 // Padding within between the button and the image
            );

            petButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectPet(pet);
                }
            });

            // Add the button to the petsContainer
            petsContainer.getChildren().add(petButton);
        }
    }

    /**
     * Handles pet selection when a pet button is clicked and highlights the selected pet.
     * @param pet The pet associated with the clicked button.
     */


    private void selectPet(Pet pet) {
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

        // Update selected pet's name
        //petNameLabel.setText(pet.getName());
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