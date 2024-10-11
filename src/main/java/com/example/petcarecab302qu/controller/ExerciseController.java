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

    @FXML
    public ImageView logoImage;

    private boolean firstPet = true;

    //@FXML
    //private Label petNameLabel;

    private Button selectedPetButton; // Keep track of the selected pet button


    private String exerciseType;

    private SqliteExerciseDAO exerciseDAO = new SqliteExerciseDAO();
    private SqlitePetDAO petDAO = new SqlitePetDAO();
    private List<Pet> pets;  // List of pets for the logged-in user

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


        ToggleGroup typeOfExercise = new ToggleGroup();
        walkRadioButton.setToggleGroup(typeOfExercise);
        runRadioButton.setToggleGroup(typeOfExercise);
        playRadioButton.setToggleGroup(typeOfExercise);

        loadPetProfiles();
    }

    /**
     * Loads the pet profiles from the database and creates a button for each pet
     */
    private void loadPetProfiles() {
        pets = petDAO.getAllPets();

        // Create a circular button for each pet and add it to the petsContainer
        for (Pet pet : pets) {
            Button petButton = new Button();
            ImageView petImageView = new ImageView();
            petImageView.setFitHeight(40);
            petImageView.setFitWidth(40);
            petImageView.setPreserveRatio(true);

            Circle clip = new Circle(20, 20, 20);
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
            //popping the image in the pet button
            petButton.setGraphic(petImageView);
            defaultButton(petButton);
            petButton.setOnAction(this::selectPet);
            petsContainer.getChildren().add(petButton);

            // Highlight the first pet as default selected
            if (firstPet) {
                petButton.setStyle("-fx-border-color: orange; -fx-background-radius: 50%; -fx-border-radius: 50%; -fx-border-width: 2px; -fx-padding: 2px;");
                selectedPetButton = petButton;
                firstPet = false;
            }
        }
    }

    /**
     * Default styling for pet buttons
     */
    private void defaultButton(Button button) {
        button.setStyle("-fx-border-color: purple; -fx-background-radius: 50%; -fx-border-radius: 50%; -fx-border-width: 2px; -fx-padding: 2px;");
    }

    /**
     * Handles the selection of a pet button, changing its border color and resets the previous
     */
    @FXML
    private void selectPet(ActionEvent event) {

        Button petButton = (Button) event.getSource();
        // Reset the previously selected button
        if (selectedPetButton != null) {
            selectedPetButton.setStyle("-fx-border-color: purple; -fx-background-radius: 50%; -fx-border-radius: 50%; -fx-border-width: 2px; -fx-padding: 2px;");
        }
        // Highlight the currently selected button
        petButton.setStyle("-fx-border-color: orange; -fx-background-radius: 50%; -fx-border-radius: 50%; -fx-border-width: 2px; -fx-padding: 2px;");

        selectedPetButton = petButton;
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