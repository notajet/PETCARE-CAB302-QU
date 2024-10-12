package com.example.petcarecab302qu.controller;
import com.example.petcarecab302qu.model.Pet;
import com.example.petcarecab302qu.model.SqlitePetDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.util.List;
import java.util.Objects;

public class PetSelectionVbox {

    private VBox petBox;
    private List<Pet> pets;
    //private Pet selectedPet; // Currently selected pet
    private Button selectedPetButton; // Currently selected pet button
    private boolean firstPet = true;

    private SqlitePetDAO petDAO = new SqlitePetDAO();

    public PetSelectionVbox(VBox petBox) {
        this.petBox = petBox;
        loadPetProfiles();
    }

    /**
     * Loads the pet profiles from the database and creates a button for each pet
     */
    private void loadPetProfiles() {
        pets = petDAO.getAllPets();

        // Create a circular button for each pet and add it to the petBox
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
            petBox.getChildren().add(petButton);

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
}